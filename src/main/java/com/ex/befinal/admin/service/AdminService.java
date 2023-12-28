package com.ex.befinal.admin.service;

import com.ex.befinal.admin.dto.AdminAllIssuesResponse;
import com.ex.befinal.admin.dto.AdminIssuesResponse;
import com.ex.befinal.admin.dto.SimpleUser;
import com.ex.befinal.global.exception.GlobalException;
import com.ex.befinal.models.User;
import com.ex.befinal.repository.JpaCommentRepository;
import com.ex.befinal.repository.JpaPostRepository;
import com.ex.befinal.repository.PostDslRepository;
import com.ex.befinal.user.repository.UserDslRepository;
import com.ex.befinal.user.repository.UserJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

  private final UserJpaRepository userJpaRepository;
  private final UserDslRepository userDslRepository;
  private final PostDslRepository postDslRepository;
  private final JpaCommentRepository commentRepository;
  private final JpaPostRepository postRepository;

  public Page<AdminIssuesResponse> getAdminUserIssues(
      PageRequest pageable, String nickname) {
    Long userId = userDslRepository.findByUserNickname(nickname)
        .orElseThrow(GlobalException.NOT_FOUND::create);
    List<AdminIssuesResponse> allByUserId = postDslRepository.findAllByUserId(userId, pageable);

    return new PageImpl<>(allByUserId);
  }


  public boolean disableUser(String kakaoId) {
    User user = userJpaRepository.findByNickName(kakaoId)
        .orElseThrow(GlobalException.NOT_FOUND::create);
    if (user.getEnable()) {
      user.setEnable(false);
      userJpaRepository.save(user);
      return user.getEnable();
    }
    user.setEnable(true);
    userJpaRepository.save(user);
    return user.getEnable();

  }

  public Page<AdminAllIssuesResponse> getAdminAllIssues(PageRequest pageable) {
    List<Long> userIds = userDslRepository.getAllIds(pageable);

    log.info("유저 아이디= {}", userIds);
    List<AdminAllIssuesResponse> issuesResponses = userIds.stream()
        .map(this::getPostDetails)
        .toList();

    return new PageImpl<>(issuesResponses);
  }

  private AdminAllIssuesResponse getPostDetails(Long id) {
    Long commentCnt = commentRepository.countByUser_Id(id)
        .orElse(0L);
    Long postCnt = postRepository.countByUser_Id(id)
        .orElse(0L);
    SimpleUser simpleUser = userDslRepository.findUserById(id)
        .orElseThrow(GlobalException.NOT_FOUND::create);
    return new AdminAllIssuesResponse(simpleUser, postCnt, commentCnt);
  }
}
