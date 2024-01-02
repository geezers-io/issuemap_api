package com.ex.befinal.comment.dto;

import com.ex.befinal.comment.entity.Comment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;

@Getter

public class CommentResponseDto {

  private final Long id;
  private final String comment;
  private String createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
  private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH:mm"));
  private final String nickname;
  private final Long postsid;

  public CommentResponseDto(Comment comment) {
    this.id = comment.getId();
    this.comment = comment.getComment();
    this.createDate = comment.getCreatedDate();
    this.modifiedDate = comment.getModifiedDate();
    this.nickname = comment.getUser().getNickName();
    this.postsid = comment.getPosts().getId();
  }

}
