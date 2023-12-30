package com.ex.befinal.file.controller;

import com.ex.befinal.file.dto.PostRequestDto;
import com.ex.befinal.file.dto.PostResponseDto;
import com.ex.befinal.file.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
// @RequiredArgsConstructor 오류가 발생해서 피드백 부탁드려요!!!
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  // 게시글 생성
  @PostMapping("/post")
    public Long save(@RequestBody final PostRequestDto params) {
        return postService.save(params);
    }

  // 게시글 조회
  @GetMapping("/post")
    public List<PostResponseDto> findAll(){
        return postService.findAll();
    }

    // 게시글 수정
  @PatchMapping("/post/{id}")
    public Long save(@PathVariable final Long id, @RequestBody final PostRequestDto params) {
      return postService.update(id, params);
    }

    // 게시글 삭제
  @DeleteMapping("/post/{id}")
    public Long delete(@PathVariable final Long id) {
        return postService.delete(id);
    }

}
