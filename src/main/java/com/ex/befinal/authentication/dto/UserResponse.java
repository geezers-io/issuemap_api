package com.ex.befinal.authentication.dto;


import com.ex.befinal.constant.UserRole;
import java.util.Date;
import lombok.Builder;

@Builder
public record UserResponse(
    Long id,
    String kakaoId,
    UserRole role,
    String nickName,
    Date createdAt,
    String provider
) {
}
