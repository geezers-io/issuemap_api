package com.ex.befinal.authentication.api;

import com.ex.befinal.authentication.dto.SignInResponse;
import com.ex.befinal.authentication.dto.UrlJson;
import com.ex.befinal.authentication.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "A. 인증 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthApi {
  private final AuthService authService;

  @Operation(summary = "로그인 URL API")
  @GetMapping("/signin-url")
  public ResponseEntity<UrlJson> getSignInUrlApi() {
    UrlJson loginUrl = authService.getLoginUrl();
    return ResponseEntity.ok(loginUrl);
  }

  @Operation(summary = "로그인 API", description = "OAuth 로그인시 인증 코드를 넘겨받은 후 첫 로그인 시 회원 가입")
  @GetMapping("/signin/{provider}")
  public ResponseEntity<SignInResponse> signInApi(

      @RequestParam String authCode,
      @PathVariable String provider
  ) {
    SignInResponse signIn = authService.signIn(provider, authCode);
    return ResponseEntity.ok(signIn);
  }
}