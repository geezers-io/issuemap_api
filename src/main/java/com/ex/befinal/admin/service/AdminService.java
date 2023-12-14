package com.ex.befinal.admin.service;

import com.ex.befinal.admin.dto.AdminIssuesResponse;
import com.ex.befinal.global.exception.GlobalException;
import com.ex.befinal.models.User;
import com.ex.befinal.repository.PostDslRepository;
import com.ex.befinal.user.repository.UserDslRepository;
import com.ex.befinal.user.repository.UserJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final UserJpaRepository userJpaRepository;
  private final UserDslRepository userDslRepository;
  private final PostDslRepository postDslRepository;

  public Page<AdminIssuesResponse> getAdminUserIssues(
      PageRequest pageable, String nickname) {
    Long userId = userDslRepository.findByUserNickname(nickname)
        .orElseThrow(GlobalException.NOT_FOUND::create);
    List<AdminIssuesResponse> allByUserId = postDslRepository.findAllByUserId(userId, pageable);

    return new PageImpl<>(allByUserId);
  }


  public boolean disableUser(String kakaoId) {
    User user = userJpaRepository.findByKakaoId(kakaoId)
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
}
