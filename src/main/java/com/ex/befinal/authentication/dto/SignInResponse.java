package com.ex.befinal.authentication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SignInResponse(

    String tokenType,
    String accessToken,
    String refreshToken,
    @Schema(nullable = true, description = "사용자 정보 객체")
    UserDetails user
) {

}
