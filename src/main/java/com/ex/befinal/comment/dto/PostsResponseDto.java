package com.ex.befinal.comment.dto;

import com.ex.befinal.comment.entity.Posts;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class PostsResponseDto {

  private final Long id;
  private final String title;
  private final String writer;
  private final String content;
  private final String createdDate;
  private final String modifiedDate;
  private final int view;
  private final Long userId;
  private final List<CommentResponseDto> comments;

  public PostsResponseDto(Posts posts, String createdDate, String modifiedDate) {
    this.id = posts.getId();
    this.title = posts.getTitle();
    this.writer = posts.getWriter();
    this.content = posts.getContent();
    this.createdDate = createdDate;
    this.view = posts.getView();
    this.userId = posts.getUser().getId();
    this.comments = posts.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    this.modifiedDate = modifiedDate;
  }
}
