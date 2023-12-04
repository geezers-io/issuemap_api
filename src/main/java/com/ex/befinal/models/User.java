package com.ex.befinal.models;

import com.ex.befinal.constant.UserRole;
import com.ex.befinal.models.embed.BasicDateColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "users")
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "kakao_id")
  private Long kakaoId;

  @Column(length = 10, nullable = false)
  private String nickName;

  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  private UserRole role;

  @Column(name = "withdraw_reason")
  private String withdrawReason;

  @Column(name = "report_count")
  private short reportCount;

  @Column(name = "created_at")
  private LocalDateTime createAt;

  @Column(name = "removed_at")
  private LocalDateTime removedAt;

  @Column(name = "disable_at")
  private LocalDateTime disableAt;

}
