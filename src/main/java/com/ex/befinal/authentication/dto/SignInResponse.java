package com.ex.befinal.authentication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SignInResponse(

    String accessToken,
    @Schema(nullable = true, description = "사용자 정보 객체")
    UserResponse user
) {

}
