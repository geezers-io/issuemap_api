package com.ex.befinal.comment.api;

import com.ex.befinal.comment.Controller.LoginUser;
import com.ex.befinal.comment.Service.CommentService;
import com.ex.befinal.comment.dto.CommentRequestDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController

public class CommentApiController {

  private final CommentService commentService;

  @PostMapping("/posts/{id}/comments")
  public ResponseEntity commentSave(@PathVariable Long id, @RequestBody CommentRequestDto dto,
                                    @LoginUser UserSessionDto userSessionDto) {

    return  ResponseEntity.ok(commentService.commentSave(userSessionDto.getNickname(), id, dto));


   @PutMapping({"/posts/{postsId}/comments/{id}"})
        public ResponseEntity<Long> update(@PathVariable Long postsId, @PathVariable Long id, @RequestBody CommentDto.Request dto){
        commentService.update(postsId, id, dto);
        return ResponseEntity.ok(id);

  }

  @DeleteMapping("/posts/{postsId}/comments/{id}")
  public ResponseEntity<Long> delete(@PathVariable Long postsId, @PathVariable Long id) {
        commentService.delete(postsId, id);
        return ResponseEntity.ok(id);
   }
  }