package com.ex.befinal.authentication.service.strategy;

import com.ex.befinal.authentication.config.KakaoProperties;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
@RequiredArgsConstructor
public class KakaoURLStrategy implements SignInUrlCreateStrategy {
  private final KakaoProperties properties;

  @Override
  public String create() {
    return String.format(urlTemplate, properties.getClientId(), properties.getRedirectUri());
  }
}
