package com.ex.befinal.tag.controller;

import com.ex.befinal.tag.service.TagCommandHandler;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TagController {

  private final TagCommandHandler commandHandler;

  @Operation(summary = "태그 생성 API")
  @PostMapping("/tag/{name}")
  public ResponseEntity<Long> createTag(@PathVariable(name = "name") String name) {
    Long id = commandHandler.create(name);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }

}
