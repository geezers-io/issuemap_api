package com.ex.befinal.admin.dto;

import java.util.Date;

public record AdminIssuesResponse(
    Long id,
    String title,
    Date createdAt
) {
}
