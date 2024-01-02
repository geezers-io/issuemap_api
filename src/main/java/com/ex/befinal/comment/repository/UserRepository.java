package com.ex.befinal.comment.repository;

import com.ex.befinal.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

Optional<User> findByUsername(String username);

User findbyNickname(String nickname);


}
