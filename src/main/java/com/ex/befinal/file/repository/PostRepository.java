package com.ex.befinal.file.repository;

import com.ex.befinal.file.dto.PostRequestDTO;
import com.ex.befinal.file.dto.PostResponseDTO;
import com.ex.befinal.models.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


public interface PostRepository extends JpaRepository<Post, Long>{
}
