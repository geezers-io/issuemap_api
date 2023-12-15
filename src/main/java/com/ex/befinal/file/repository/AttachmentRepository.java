package com.ex.befinal.file.repository;

import com.nimbusds.openid.connect.sdk.assurance.evidences.attachment.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    static Thread save(com.ex.befinal.models.Attachment entity) {
        return null;
    }


//    static com.ex.befinal.models.Attachment save(com.ex.befinal.models.Attachment attachment) {
//
//        return null;
//    }

    // 파일 이름으로 파일 엔터티 찾기
//    Optional<Attachment> findByName(String name);

    // 파일 post_id로 파일 엔터티 찾기
//    Optional<Attachment> findById(long post_id);

}