package com.ex.befinal.issue.service;

import com.ex.befinal.issue.domain.HotIssueSummaryProjection;
import com.ex.befinal.issue.domain.IssueSummary;
import com.ex.befinal.repository.JpaPostRepository;
import com.ex.befinal.repository.PostDslRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssueService {
  private final PostDslRepository postDslRepository;
  private final JpaPostRepository postRepository;

  public List<IssueSummary> getHotIssues() {
    List<HotIssueSummaryProjection> projections = postRepository.findPosts();
    List<IssueSummary> issueSummaries = new ArrayList<>();
    for (HotIssueSummaryProjection projection : projections) {
      IssueSummary issueSummary = new IssueSummary(
          projection.getId(),
          projection.getTitle(),
          projection.getThumbnailUrl(),
          projection.getDescription(),
          projection.getTags(),
          projection.getCreatedAt() // 이 부분은 Date 타입이어야 합니다. 필요하다면 타입 변환을 수행하세요.
      );
      issueSummaries.add(issueSummary);
    }
    return issueSummaries;
  }



  public List<IssueSummary> getAllIssues() {
    List<IssueSummary> allIssues = postDslRepository.findAllIssues();

    return allIssues;
  }

  public List<IssueSummary> getGeoIssues(Double latitude, Double longitude) {
    List<IssueSummary> geoIssues = postDslRepository.findGeoIssues(latitude, longitude);
    return geoIssues;
  }
}
