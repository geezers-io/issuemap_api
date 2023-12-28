package com.ex.befinal.admin.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.util.Date;
import java.util.Objects;

public record RecentRegisteredUser(
    Long id,

    Date createdAt,
    String nickname,
    Short reportCount,
    Date disableAt,
    Boolean enable
) {

  @QueryProjection
  public RecentRegisteredUser(
      Long id,
      Date createdAt,
      String nickname,
      Short reportCount,
      Date disableAt,
      Boolean enable) {
    this.id = id;
    this.createdAt = createdAt;
    this.nickname = nickname;
    this.reportCount = reportCount;
    this.disableAt = disableAt;
    this.enable = enable;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecentRegisteredUser that = (RecentRegisteredUser) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(createdAt, that.createdAt) &&
        Objects.equals(nickname, that.nickname) &&
        Objects.equals(reportCount, that.reportCount) &&
        Objects.equals(disableAt, that.disableAt) &&
        Objects.equals(enable, that.enable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, nickname, reportCount, disableAt, enable);
  }
}
