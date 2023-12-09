package com.ex.befinal.authentication.dto;

import java.util.Date;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KakaoTokenResponse {

    @SuppressWarnings({"checkstyle:Indentation", "checkstyle:MemberName"})
    private String token_type;

    @SuppressWarnings({"checkstyle:Indentation", "checkstyle:MemberName"})
    private String access_token;

    @SuppressWarnings({"checkstyle:Indentation", "checkstyle:MemberName"})
    private Date expires_in;

    @SuppressWarnings({"checkstyle:Indentation", "checkstyle:MemberName"})
    private String refresh_token;

    @SuppressWarnings({"checkstyle:Indentation", "checkstyle:MemberName"})
    private Date refresh_token_expires_in;


}
