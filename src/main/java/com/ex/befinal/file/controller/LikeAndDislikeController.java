package com.ex.befinal.file.controller;

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

    @PostMapping
    public ResponseEntity<LikeAndDislike> likeAndDislike(@RequestBody @Valid LikeAndDislikeDto likeAndDislikeDto, String jtwToken) throws IOException {
        // likeAndDislikeService.likeAndDislike(likeAndDislikeDto);
        return new ResponseEntity<>(likeAndDislikeDto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<LikeAndDislikeDto> unLikeAndDislike(@RequestBody @Valid LikeAndDislikeDto likeAndDislikeDto) throws IOException {
        // likeAndDislikeService.likeAndDislike(likeAndDislikeDto);
        return new ResponseEntity<>(likeAndDislikeDto, HttpStatus.OK);
    }

}
