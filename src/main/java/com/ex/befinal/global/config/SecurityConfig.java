package com.ex.befinal.global.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableRedisHttpSession
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    disableMvcSetting(http);

    http
        .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers(
                "/swaager-ui/**", "/docs/**"
                ).permitAll()
            .anyRequest().authenticated());


    return http.build();
  }


  private void disableMvcSetting(HttpSecurity http) throws Exception {
    http
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable)
        .logout(AbstractHttpConfigurer::disable);

  }

}
