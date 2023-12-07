package com.ex.befinal.authentication.service;

import com.ex.befinal.authentication.dto.UrlJson;
import com.ex.befinal.authentication.service.strategy.SignInStrategy;
import com.ex.befinal.authentication.service.strategy.SignInUrlCreateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
  private final SignInStrategy signInStrategy;

  /**
   * AuthVendor 에 따른 각 Vendor 로그인을 수행하는 URL 을 반환합니다.
   */

  public UrlJson getLoginUrl() {
    SignInUrlCreateStrategy urlCreateStrategy = signInStrategy.getStrategy();
    return new UrlJson(urlCreateStrategy.create());
  }

  /**
   * 인증 코드를 기반으로 Resource Server 에 토큰 정보를 갱신하고 사용자 정보를 조회하여 LoginResponse 객체를 반환합니다.
   */


}
