package com.ex.befinal.file.dto;

import com.ex.befinal.models.Post;
import lombok.*;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeAndDislikeDto {
    private User userId;
    private Post postId;
}
