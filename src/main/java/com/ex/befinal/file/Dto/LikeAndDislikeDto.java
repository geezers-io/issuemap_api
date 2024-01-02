package com.ex.befinal.file.Dto;

import com.ex.befinal.models.Post;
import lombok.*;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeAndDislikeDto {

  private User userId;
  private Post postId;
}
