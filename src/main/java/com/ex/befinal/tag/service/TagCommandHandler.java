package com.ex.befinal.tag.service;

import com.ex.befinal.models.Tag;
import com.ex.befinal.tag.infrastructure.TagJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagCommandHandler {
  private final TagJpaRepository tagRepository;

  public Long create(String name) {
    Tag result = new Tag(name);
    result = tagRepository.save(result);
    return result.getId();
  }

}
