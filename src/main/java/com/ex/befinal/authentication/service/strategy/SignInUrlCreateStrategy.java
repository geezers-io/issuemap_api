package com.ex.befinal.authentication.service.strategy;

public interface SignInUrlCreateStrategy {
  String urlTemplate = "https://kauth.kakao.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code";

  String create();
}
