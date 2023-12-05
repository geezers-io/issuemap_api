package com.ex.befinal.tag.infrastructure;

import com.ex.befinal.models.Tag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagJpaRepository extends JpaRepository<Tag, Long> {
  Optional<Tag> findByName(String name);
}
