package com.ex.befinal.issue.service;

import com.ex.befinal.issue.domain.GeoIssueSummaryProjection;
import com.ex.befinal.issue.domain.HotIssueSummaryProjection;
import com.ex.befinal.issue.domain.IssueSummary;
import com.ex.befinal.repository.JpaPostRepository;
import com.ex.befinal.repository.PostDslRepository;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
    List<HotIssueSummaryProjection> projections = postRepository.findHotPosts();
    List<IssueSummary> issueSummaries = new ArrayList<>();
    for (HotIssueSummaryProjection projection : projections) {
      Set<String> tagSet = new HashSet<>();
      if (projection.getTags() != null && !projection.getTags().isEmpty()) {
        tagSet = Stream.of(projection.getTags().split(","))
            .map(String::trim)
            .collect(Collectors.toSet());
      }
      IssueSummary issueSummary = new IssueSummary(
          projection.getId(),
          projection.getTitle(),
          projection.getThumbnailUrl(),
          projection.getDescription(),
          tagSet,
          projection.getCreatedAt()
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
    List<GeoIssueSummaryProjection> geoIssues =
        postRepository.findGeoPosts(latitude, longitude);
    List<IssueSummary> issueSummaries = new ArrayList<>();
    for (GeoIssueSummaryProjection projection : geoIssues) {
      Set<String> tagSet = new HashSet<>();
      if (projection.getTags() != null && !projection.getTags().isEmpty()) {
        tagSet = Stream.of(projection.getTags().split(","))
            .map(String::trim)
            .collect(Collectors.toSet());
      }
      IssueSummary issueSummary = new IssueSummary(
          projection.getId(),
          projection.getTitle(),
          projection.getThumbnailUrl(),
          projection.getDescription(),
          tagSet,
          projection.getCreatedAt() 
      );
      issueSummaries.add(issueSummary);
    }
    return issueSummaries;
  }
}
