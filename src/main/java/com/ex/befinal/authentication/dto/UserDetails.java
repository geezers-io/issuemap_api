package com.ex.befinal.authentication.dto;

import com.ex.befinal.constant.UserRole;
import java.util.Date;
import lombok.Builder;

@Builder
public record UserDetails(
    Long id,

    Long kakaoId,

    UserRole role,

    String nickName,

    Date creatAt,

    String provider
) {
}
