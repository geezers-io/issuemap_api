package com.ex.befinal.constant;

import lombok.Getter;

@Getter
public enum LikeStatus {
  LIKE("좋아요"),
  DISLIKE("싫어요"),
  NULL("디폴트");

  private final String status;

  LikeStatus(String status) {
    this.status = status;
  }
}
