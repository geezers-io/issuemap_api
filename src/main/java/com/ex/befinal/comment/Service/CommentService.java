package com.ex.befinal.comment.Service;

import com.ex.befinal.comment.dto.CommentRequestDto;
import com.ex.befinal.comment.entity.Comment;
import com.ex.befinal.comment.entity.Posts;
import com.ex.befinal.comment.repository.CommentRepository;
import com.ex.befinal.models.User;
import com.ex.befinal.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service

public class CommentService {

  private final CommentRepository commentRepository;
  private final UserJpaRepository userJpaRepository;
  private final Post

  @Transactional
  public Long commentSave(String nickname, Long id, CommentRequestDto dto) {
    User user = UserJpaRepository.findByNickname(nickname);
    Posts posts = postsRepository.findById(id).orELseThrow(() ->
        new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));

    dto.setUser(user);
    dto.setPosts(posts);

    Comment comment = dto.toEntity();
    commentRepository.save(comment);

    return dto.getId();
  }
}
