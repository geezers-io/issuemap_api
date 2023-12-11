package com.ex.befinal.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class SecurityTest {



  @Test
  public void getInMemoryTest() {
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("user")
        .password("password")
        .build();
    InMemoryUserDetailsManager memoryUserDetailsManager = new InMemoryUserDetailsManager(user);
    memoryUserDetailsManager.deleteUser("user");
  }
}
