package com.ex.befinal.file.service;

import com.ex.befinal.file.dto.AttachmentDTO;
import com.ex.befinal.file.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 파일 저장 기능 Service
@RequiredArgsConstructor
@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @Transactional
    public Long save(AttachmentDTO attachmentDTO){
        return AttachmentRepository.save(attachmentDTO.toEntity()).getId();
    }
}
