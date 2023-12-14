package com.ex.befinal.admin.dto;

import java.util.Date;

public record AdminAllIssuesResponse(
    SimpleUser simpleUser,
    Long issuesCount,
    Long commentCount

) {
}
