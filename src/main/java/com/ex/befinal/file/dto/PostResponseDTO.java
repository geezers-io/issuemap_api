package com.ex.befinal.file.dto;

import com.ex.befinal.models.Post;
import com.ex.befinal.models.User;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class PostResponseDTO {
    private Long id; // pk
    private User user; // 사용자
    private String title; // 제목
    private String description; // 내용
    private short reportCount; // 조회수
    private BigDecimal latitude; // 경도
    private BigDecimal longitude; // 위도
    private Date createdAt; // 등록일
    private Date removedAt; // 삭제 날짜
    private Date disabledAt; // 삭제 된 날짜

    public PostResponseDTO(Post entity) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.reportCount = reportCount;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
        this.removedAt = removedAt;
        this.disabledAt = disabledAt;
    }
}
