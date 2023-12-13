package com.ex.befinal.user.repository;

import com.ex.befinal.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
  Optional<User> findByKakaoId(String kakaoId);

  boolean existsByNickName(String nickname);

  Optional<User> findByNickName(String nickName);
}
