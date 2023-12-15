package com.ex.befinal.user.service;

import com.ex.befinal.authentication.service.TokenManager;
import com.ex.befinal.models.User;
import com.ex.befinal.repository.JpaCommentRepository;
import com.ex.befinal.repository.JpaPostRepository;
import com.ex.befinal.user.domain.UserProfile;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final TokenManager tokenManager;
  private final JpaPostRepository postRepository;
  private final JpaCommentRepository commentRepository;

  public UserProfile getUserCount() {
    User user = tokenManager.getUser();
    Long postCount = postRepository.countByUser_Id(user.getId())
            .orElse(0L);
    Long commentCount = commentRepository.countByUser_Id(user.getId())
        .orElse(0L);
    return new UserProfile(user.getNickName(), postCount, commentCount);
  }
}
