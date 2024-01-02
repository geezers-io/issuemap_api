package com.ex.befinal.comment.dto;

import com.ex.befinal.comment.entity.Comment;
import com.ex.befinal.comment.entity.Posts;
import com.ex.befinal.models.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CommentRequestDto {

  private Long id;
  private String comment;
  private String createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
  private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH.mm"));
  private User user;
  private Posts posts;

  public Comment toEntity() {
    Comment comments = Comment.builder()
        .id(id)
        .comment(comment)
        .createdDate(createDate)
        .modifiedDate(modifiedDate)
        .user(user)
        .posts(posts)
        .build();

      return comments;
  }
}
