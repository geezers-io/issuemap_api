package com.ex.befinal.user.api;

import com.ex.befinal.authentication.dto.CustomUserDetails;
import com.ex.befinal.authentication.service.TokenManager;
import com.ex.befinal.user.domain.UserProfile;
import com.ex.befinal.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "C. 유저 API")
@RequestMapping("/user")
public class UserApi {


  private final UserService userService;

  @Operation(summary = "본인 댓글 이슈 개수 가져오기 API")
  @GetMapping("/profile")
  public ResponseEntity<UserProfile> nickNameAndCount() {
    UserProfile userProfile = userService.getUserCount();
    return ResponseEntity.status(HttpStatus.OK).body(userProfile);
  }
}
