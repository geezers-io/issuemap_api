package com.ex.befinal.authentication.config;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.kakao")
public class KakaoProperties {
  @NotBlank
  private String clientId;

  @NotBlank
  private String redirectUri;
}
