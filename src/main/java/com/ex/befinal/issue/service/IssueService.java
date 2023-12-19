package com.ex.befinal.issue.service;

import com.ex.befinal.issue.domain.IssueSummary;
import com.ex.befinal.repository.JpaPostRepository;
import com.ex.befinal.repository.PostDslRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssueService {
  private final PostDslRepository postDslRepository;

  public List<IssueSummary> getHotIssues() {
    List<IssueSummary> hotIssues = postDslRepository.findHotIssues();
    return hotIssues;
  }

  public List<IssueSummary> getAllIssues() {
    List<IssueSummary> allIssues = postDslRepository.findAllIssues();

    return allIssues;
  }

  public List<IssueSummary> getGeoIssues(Double latitude, Double longitude) {
    return null;
  }
}
