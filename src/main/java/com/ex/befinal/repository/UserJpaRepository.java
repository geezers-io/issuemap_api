package com.ex.befinal.repository;

import com.ex.befinal.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
  Optional<User> findByKakaoId(Long kakaoId);

  boolean existsByNickName(String nickname);
}
