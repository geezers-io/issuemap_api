package com.ex.befinal.admin.dto;

import java.util.Date;

public record AdminIssuesResponse(
    String title,
    Date createdAt
) {
}
