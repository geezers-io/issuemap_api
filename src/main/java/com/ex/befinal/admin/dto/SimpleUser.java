package com.ex.befinal.admin.dto;

import java.util.Date;

public record SimpleUser(
    String nickname,
    boolean userEnable,
    Date userCreatedAt
) {
}
