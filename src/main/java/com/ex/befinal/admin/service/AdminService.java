package com.ex.befinal.admin.service;

import com.ex.befinal.admin.dto.AdminIssuesResponse;
import com.ex.befinal.global.exception.GlobalException;
import com.ex.befinal.models.User;
import com.ex.befinal.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final UserJpaRepository userJpaRepository;

  public Page<AdminIssuesResponse> getAdminUserIssues(
      PageRequest pageable, String nickname) {
    return null;
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
