package com.ex.befinal.repository;

import com.ex.befinal.models.Comment;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCommentRepository extends JpaRepository<Comment, Long> {

  Optional<Long> countByUser_Id(Long userId);
}
