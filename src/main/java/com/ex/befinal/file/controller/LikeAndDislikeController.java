package com.ex.befinal.file.controller;

import com.ex.befinal.file.Dto.LikeAndDislikeDto;
import com.ex.befinal.file.service.LikeAndDislikeService;
import com.ex.befinal.models.LikeAndDislike;
import groovy.util.logging.Slf4j;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likeAndDislike")
public class LikeAndDislikeController {

    private final LikeAndDislikeService likeAndDislikeService;

    // 좋아요
    @PostMapping
    public ResponseEntity<LikeAndDislikeDto> likeAndDislike(@RequestBody @Valid LikeAndDislikeDto likeAndDislikeDto) throws IOException {
        likeAndDislikeService.likeAndDislike(likeAndDislikeDto);
        return new ResponseEntity<>(likeAndDislikeDto, HttpStatus.CREATED);
    }

    // 싫어요
    @DeleteMapping
    public ResponseEntity<LikeAndDislikeDto> unLikeAndDislike(@RequestBody @Valid LikeAndDislikeDto likeAndDislikeDto) throws IOException {
        likeAndDislikeService.likeAndDislike(likeAndDislikeDto);
        return new ResponseEntity<>(likeAndDislikeDto, HttpStatus.OK);
    }

}
