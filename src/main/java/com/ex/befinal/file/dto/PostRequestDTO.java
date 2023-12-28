package com.ex.befinal.file.dto;

import com.ex.befinal.models.Post;
import com.ex.befinal.models.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequestDTO {

    private String title; // 제목
    private String description; // 내용
    private User user; // 사용자

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .description(description)
                .user(user)
                .build();
    }
}
