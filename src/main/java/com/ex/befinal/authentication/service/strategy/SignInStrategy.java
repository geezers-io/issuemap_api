package com.ex.befinal.authentication.service.strategy;

import com.ex.befinal.authentication.config.KakaoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SignInStrategy {
  private final KakaoProperties kakaoProperties;

  public SignInUrlCreateStrategy getStrategy() {
    return new KakaoURLStrategy(kakaoProperties);
  }
}
