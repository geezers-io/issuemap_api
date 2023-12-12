package com.ex.befinal.authentication.dto;

import com.ex.befinal.constant.UserRole;
import com.ex.befinal.models.User;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
  private final User user;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()));
  }

  public User getUser() {
    return user;
  }


  @Override
  public String getPassword() {
    return user.getKakaoId();
  }

  @Override
  public String getUsername() {
    return user.getNickName();
  }

  @Override
  public boolean isAccountNonExpired() {
    return user.getDisableAt().before(new Date()) || user.getDisableAt() == null;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return user.getEnable();
  }
}
