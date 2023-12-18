package com.ex.befinal.issue.domain;

import com.querydsl.core.annotations.QueryProjection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    Set<String> tags,
    Date createdAt
) {
  @QueryProjection
  public IssueSummary(
      Long id,
      String title,
      String thumbnailUrl,
      String description,
      Set<String> tags,
      Date createdAt) {
    this.id = id;
    this.title = title;
    this.thumbnailUrl = thumbnailUrl;
    this.description = description;
    this.tags = tags;
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IssueSummary that = (IssueSummary) o;
    return Objects.equals(id, that.id)
        && Objects.equals(title, that.title)
        && Objects.equals(thumbnailUrl, that.thumbnailUrl)
        && Objects.equals(description, that.description)
        && Objects.equals(tags, that.tags)
        && Objects.equals(createdAt, that.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        title,
        thumbnailUrl,
        description,
        tags,
        createdAt);
  }
}
