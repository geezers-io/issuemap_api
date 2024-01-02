package com.ex.befinal.comment.Controller;

import com.ex.befinal.comment.dto.CommentResponseDto;
import com.ex.befinal.comment.dto.PostsResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor

public class PostsIndexController {

  private final PostsService postsService;

  @PostMapping("/posts")
  public ResponseEntity save(@RequestBody PostsResponseDto dto, @LoginUser UserSessionDto userSessionDto){
      return ResponseEntity.ok(postsService.save(userSessionDto.getNickname(), dto));
  }
  @GetMapping("/posts/read/{id}")
  public String read(@PathVariable Long id, @LoginUser UserSessionDto user, Model model) {
    PostsResponseDto dto = postsService.findById(id);
    List<CommentResponseDto> comments = dto.getComments();

    if (comments != null && !comments.isEmpty()) {
      model.addAttribute("comments", comments);
    }

    if (user != null) {
      model.addAttribute("user", user.getNickname());

      if (dto.getUserId().equals(user.getId())) {
        model.addAttribute("writer", true);
      }
    }
    postsService.updateView(id);
    model.addAttribute("posts", dto);
    return "posts/posts-read";
  }
}
