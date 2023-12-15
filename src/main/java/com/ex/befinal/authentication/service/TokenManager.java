package com.ex.befinal.authentication.service;

import com.ex.befinal.authentication.dto.CustomUserDetails;
import com.ex.befinal.models.User;
import com.ex.befinal.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenManager {

  private final UserJpaRepository userJpaRepository;

  private Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  public String getNickname() {
    return getDetails().getUsername();
  }

  public User getUser() {
    return getDetails().getUser();
  }

  public String getKakaoId() {
    return getDetails().getPassword();
  }

  public Long getId() {
    return getUser().getId();
  }

  private CustomUserDetails getDetails() {
    return (CustomUserDetails) getAuthentication().getPrincipal();
  }
}
