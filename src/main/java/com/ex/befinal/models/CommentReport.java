package com.ex.befinal.models;

import com.ex.befinal.models.embed.BasicDateColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "comment_reports")
public class CommentReport {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "reason")
  private String reason;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reporter_id", referencedColumnName = "id")
  private User reporterId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "target_comment_id", referencedColumnName = "id")
  private Comment targetCommentId;

  @Column(name = "created_at")
  private LocalDateTime createAt;
}
