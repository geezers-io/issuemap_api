package com.ex.befinal.file.service;

import com.ex.befinal.authentication.provider.JwtTokenProvider;
import com.ex.befinal.file.Dto.LikeAndDislikeDto;
import com.ex.befinal.file.repository.LikeAndDislikeRepository;
import com.ex.befinal.models.LikeAndDislike;
import com.ex.befinal.repository.PostDslRepository;
import com.ex.befinal.user.repository.UserJpaRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeAndDislikeService {

  private final LikeAndDislikeRepository likeAndDislikeRepository;
  private final UserJpaRepository userJpaRepository;
  private final PostRepository postRepository;
  private final JwtTokenProvider jwtTokenProvider;

  public void likeAndDislike(LikeAndDislikeDto likeAndDislikeDto, String jwtToken) throws IOException {
      if (findLikeAndDislikeWithUserAndPost(likeAndDislikeDto).isPresent()) {
          throw new CustomException("이미 좋아요를 눌렀습니다.");
      }
    LikeAndDislike likeAndDislike = LikeAndDislike.builder()
                .post(likeAndDislikeDto.getPostId())
                .build();
    likeAndDislikeRepository.save(likeAndDislike);
    }
    public void unLikeAndDislike(LikeAndDislikeDto likeAndDislikeDto, String jwtToken) throws IOException {
        validateToken(likeAndDislikeDto, jwtToken);

        Optional<LikeAndDislike> likeAndDislikeOpt = findLikeAndDislikeWithUserAndPost(likeAndDislikeDto);

        if(likeAndDislikeOpt.isEmpty()) {
            throw new CustomException("좋아요를 찾을 수 없습니다.");
        }
        likeAndDislikeRepository.delete(likeAndDislikeOpt.get());
    }

  public void validateToken(LikeAndDislikeDto likeAndDislikeDto, String jwtToken){
    }

  public Optional<LikeAndDislike> findLikeAndDislikeWithUserAndPost(LikeAndDislikeDto likeAndDislikeDto) {
        return likeAndDislikeRepository
                .findLikeAndDislikeByUserAndPost(user.getId(), likeAndDislikeDto.getPostId());
    }
}
