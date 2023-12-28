package com.ex.befinal.issue.service;

import com.ex.befinal.issue.domain.GeoIssueSummaryProjection;
import com.ex.befinal.issue.domain.HotIssueSummaryProjection;
import com.ex.befinal.issue.domain.IssueSummary;
import com.ex.befinal.issue.domain.IssueSummaryProjection;
import com.ex.befinal.issue.domain.PostCountProjection;
import com.ex.befinal.repository.JpaPostRepository;
import com.ex.befinal.repository.PostDslRepository;
import com.ex.befinal.utils.TimeFilterRange;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssueService {
  private final PostDslRepository postDslRepository;
  private final JpaPostRepository postRepository;
  private final TimeFilterRange timeFilterRange;

  public Page<IssueSummary> getAroundPage(
      PageRequest pageable,
      Date startDate,
      Date endDate,
      String title,
      Double latitude,
      Double longitude) {
    Date start = timeFilterRange.startTime(startDate);
    Date end = timeFilterRange.endTime(endDate);
    List<GeoIssueSummaryProjection> geoIssueSummaryProjectionList = postRepository.findAroundPage(
        title,
        latitude,
        longitude,
        start,
        end,
        pageable.getPageSize(),
        pageable.getOffset()
    );
    List<IssueSummary> issueSummaries = mapToIssueSummary(geoIssueSummaryProjectionList);
    return new PageImpl<>(issueSummaries);
  }

  public Page<IssueSummary> getAllPage(
      PageRequest pageable,
      Date startDate,
      Date endDate,
      String title) {
    Date start = timeFilterRange.startTime(startDate);
    Date end = timeFilterRange.endTime(endDate);

    List<IssueSummary> allIssues = postDslRepository.findAllIssues(title, start, end, pageable);

    return new PageImpl<>(allIssues);
  }


  public List<IssueSummary> getHotIssues() {
    List<HotIssueSummaryProjection> projections = postRepository.findHotPosts();
    List<IssueSummary> issueSummaries = mapToIssueSummary(projections);
    return issueSummaries;
  }



  public List<IssueSummary> getAllIssues() {
    List<IssueSummary> allIssues = postDslRepository.findAllIssues();

    return allIssues;
  }

  public List<IssueSummary> getGeoIssues(Double latitude, Double longitude) {
    List<GeoIssueSummaryProjection> geoIssues =
        postRepository.findGeoPosts(latitude, longitude);
    List<IssueSummary> issueSummaries = mapToIssueSummary(geoIssues);
    return issueSummaries;
  }

  private List<IssueSummary> mapToIssueSummary(List<? extends IssueSummaryProjection> projections) {
    List<IssueSummary> issueSummaries = new ArrayList<>();
    for (IssueSummaryProjection projection : projections) {
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
