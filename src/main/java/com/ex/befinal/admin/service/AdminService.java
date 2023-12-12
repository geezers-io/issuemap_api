package com.ex.befinal.admin.service;

import com.ex.befinal.global.exception.GlobalException;
import com.ex.befinal.models.User;
import com.ex.befinal.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final UserJpaRepository userJpaRepository;

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
