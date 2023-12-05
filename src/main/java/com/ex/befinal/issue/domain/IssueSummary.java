package com.ex.befinal.issue.domain;

import java.util.Date;
import java.util.List;

/**
 * 게시글 데이터를 의미합니다.
 *
 * @param id
 * @param title
 * @param thumbnailUrl 대표 이미지
 * @param description
 * @param tags
 * @param createdAt
 */
public record IssueSummary(
    Long id,
    String title,
    String thumbnailUrl,
    String description,
    List<String> tags,
    Date createdAt
) {}
