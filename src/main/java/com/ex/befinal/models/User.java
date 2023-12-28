package com.ex.befinal.models;

import com.ex.befinal.constant.UserRole;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "users")
@AllArgsConstructor
@Builder
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "kakao_id")
  private String kakaoId;

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
  private Date createAt;

  @Column(name = "removed_at")
  private Date removedAt;

  @Column(name = "disable_at")
  private Date disableAt;

  @Column(name = "provider")
  private String provider;

  @Column(name = "enable")
  private boolean enable;

  public void setEnable(boolean enable) {
    this.enable = enable;
  }

  public boolean getEnable() {
    return this.enable;
  }

  @OneToMany(
          mappedBy = "user",
          cascade = CascadeType.ALL, // User가 삭제 될 때 연관된 엔티티인 LikeAndDislike도 삭제
          orphanRemoval = true,
          fetch = FetchType.LAZY)

  private List<LikeAndDislike> likeAndDislikes;
}



