package com.ex.befinal.authentication.service.client;

import com.ex.befinal.authentication.dto.KakaoTokenResponse;
import com.ex.befinal.authentication.dto.KakaoUserDetailsResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class KakaoClient {

  public KakaoTokenResponse getToken(String code, ClientRegistration provider) {
    return WebClient.create()
        .post()
        .uri(provider.getProviderDetails().getTokenUri())
        .headers(header -> {
          header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
          header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
        })
        .bodyValue(tokenRequest(code, provider))
        .retrieve()
        .bodyToFlux(KakaoTokenResponse.class)
        .blockFirst();
  }

  private MultiValueMap<String, String> tokenRequest(String code, ClientRegistration provider) {
    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    formData.add("grant_type", "authorization_code");
    formData.add("client_id", provider.getClientId());
    formData.add("redirect_uri", provider.getRedirectUri());
    formData.add("client_secret", provider.getClientSecret());
    formData.add("code", code);

    return formData;
  }

  public KakaoUserDetailsResponse getUserAttributes(ClientRegistration provider, KakaoTokenResponse tokenResponse) {
    return WebClient.create()
        .get()
        .uri(provider.getProviderDetails().getUserInfoEndpoint().getUri())
        .headers(header -> header.setBearerAuth(tokenResponse.getAccess_token()))
        .retrieve()
        .bodyToFlux(KakaoUserDetailsResponse.class)
        .blockFirst();
  }
}
