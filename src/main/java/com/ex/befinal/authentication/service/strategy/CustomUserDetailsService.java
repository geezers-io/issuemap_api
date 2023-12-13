package com.ex.befinal.authentication.service.strategy;

import com.ex.befinal.authentication.dto.CustomUserDetails;
import com.ex.befinal.models.User;
import com.ex.befinal.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserJpaRepository userJpaRepository;

  @Override
  public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {
    User user = userJpaRepository.findByNickName(nickName)
        .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 닉네임"));
    return new CustomUserDetails(user);
  }
}
