package com.ex.befinal.file.dto;

import com.ex.befinal.models.Attachment;
import com.ex.befinal.models.Post;
import lombok.Builder;
import lombok.Data;

// 업로드 결과 처리용 DTO
@Data
public class AttachmentDTO {

    private Long id;
    private String name;
    private Post post;
    private String ext;
    private String uploadPath;

    public Attachment toEntity() {
        return Attachment.builder()
                .id(this.id)
                .name(this.name)
                .post(this.post)
                .ext(this.ext)
                .uploadPath(this.uploadPath)
                .build();
    }

    @Builder
    public AttachmentDTO(String name, String ext){
        this.id = id;
        this.name = name;
        this.post = post;
        this.ext = ext;
        this.uploadPath = uploadPath;
    }
}
