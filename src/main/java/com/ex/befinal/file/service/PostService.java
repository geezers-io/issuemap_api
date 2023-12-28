package com.ex.befinal.file.service;

import ch.qos.logback.core.spi.ErrorCodes;
import com.ex.befinal.file.dto.PostRequestDTO;
import com.ex.befinal.file.dto.PostResponseDTO;
import com.ex.befinal.file.exception.CustomException;
import com.ex.befinal.file.repository.PostRepository;
import com.ex.befinal.models.Post;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시글 생성
    @Transactional
    public Long save(final PostRequestDTO params) {
        Post entity = postRepository.save(params.toEntity());
        return entity.getId();
    }

    // 게시글 조회
    public List<PostResponseDTO> findAll(){
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createDate");
        List<Post> list = postRepository.findAll(sort);
        return list.stream().map(PostResponseDTO::new).collect(Collectors.toList());
    }

    //게시글 수정
    @Transactional
    public Long update(final Long id, final PostRequestDTO params){
        Post entity = postRepository.findById(id).orElseThrow(() -> new CustomException("게시글 수정 완료"));
        entity.update(params.getTitle(), params.getDescription(), params.getUser(), params.getUser().getCreateAt());
        return id;
    }

    // 게시글 삭제
    @Transactional
    public Long delete(final Long id) {
        Post entity = postRepository.findById(id).orElseThrow(() -> new CustomException("게시글 삭제 완료"));
        entity.delete();
        return id;
    }
}
