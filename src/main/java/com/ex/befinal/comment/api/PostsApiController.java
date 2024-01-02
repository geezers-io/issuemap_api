package com.ex.befinal.comment.api;

import com.ex.befinal.comment.Controller.LoginUser;
import com.ex.befinal.comment.Service.PostsService;
import com.ex.befinal.comment.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



  @RequestMapping("/api")
  @RequiredArgsConstructor
  @RestController
  public class PostsApiController {

    private final PostsService postService;

    @PostMapping("/posts")
    public ResponseEntity save(@RequestBody PostsResponseDto dto, @LoginUser userSessionDto) {
        return ResponseEntity.ok(postService.save(userSessionDto.getNickname(), dto));
    }

}
