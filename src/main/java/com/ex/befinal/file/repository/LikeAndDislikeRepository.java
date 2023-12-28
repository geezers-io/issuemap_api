package com.ex.befinal.file.repository;

import com.ex.befinal.models.LikeAndDislike;
import com.ex.befinal.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface LikeAndDislikeRepository extends JpaRepository {
    Optional<LikeAndDislike> findLikeAndDislikeByUserAndPost(Long id, Post post);
}
