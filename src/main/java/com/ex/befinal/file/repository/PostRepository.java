package com.ex.befinal.file.repository;

import com.ex.befinal.file.dto.PostRequestDTO;
import com.ex.befinal.file.dto.PostResponseDTO;
import com.ex.befinal.models.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


public interface PostRepository extends JpaRepository<Post, Long>{

//    public PostService(PostRepository postRepository){
//
//        this.postRepository = postRepository;
//    }
//
//    // 게시글 등록
//    @Transactional
//    public Long save(final Post params) {
//        Post entity = postRepository.save(params.toEntity());
//        return entity.getId();
//    }
//
//    // 게시글 조회
//    public List<PostResponseDTO> findAll(){
//        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdAt");
//        List<Post> list = postRepository.findAll(sort);
//        return list.stream().map(PostResponseDTO::new).collect(Collectors.toList());
//    }
//
//    // 게시글 수정
//    @Transactional
//    public Long update(final Long id, final PostRequestDTO params){
//        Post entity = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
//        entity.update(params.getTitle(), params.getDescription(), params.getUser());
//        return id;
//    }
//
//    // 게시글 삭제
}
