package com.ex.befinal.constant;

import lombok.Getter;

@Getter
public enum UserRole {

  USER("일반 회원"),
  ADMIN("관리자");

  private final String name;

  UserRole(String name) {
    this.name = name;
  }
}
