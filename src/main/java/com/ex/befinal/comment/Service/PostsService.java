package com.ex.befinal.comment.Service;


import com.ex.befinal.comment.dto.PostsResponseDto;
import com.ex.befinal.comment.entity.Posts;
import com.ex.befinal.models.User;
import com.ex.befinal.user.repository.UserJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

  private final PostsRepository postsRepository;
  private final UserJpaRepository userJpaRepository;

  @Transactional
  public Long save(String nickname, PostsResponseDto dto) {
    Optional<User> user = userJpaRepository.findByNickName(nickname);
    dto.setUser(user);

    Posts posts = dto.toEntity();
    postsRepository.save(posts);

    return posts.getId();
  }
}
