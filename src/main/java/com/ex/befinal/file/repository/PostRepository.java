package com.ex.befinal.file.repository;

import com.ex.befinal.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long>{
}
