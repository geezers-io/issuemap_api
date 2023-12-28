package com.ex.befinal.file.controller;

import com.ex.befinal.file.dto.PostRequestDto;
import com.ex.befinal.file.dto.PostResponseDto;
import com.ex.befinal.file.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

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
