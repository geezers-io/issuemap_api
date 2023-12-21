package com.ex.befinal.issue.api;

import com.ex.befinal.issue.domain.IssueSummary;
import com.ex.befinal.issue.domain.MainIssues;
import com.ex.befinal.issue.service.IssueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/issue")
@RequiredArgsConstructor
@Tag(name = "B. 이슈 API")
public class IssueApi {

  private final IssueService issueService;

  @Operation(summary = "메인 페이지 이슈 API", description = "hot, geo, all을 기반으로 게시글을 가져옵니다.")
  @GetMapping
  public ResponseEntity<MainIssues> getMainPage(
      @Parameter(description = "사용자 위도", required = true, example = "33.450701")
      @RequestParam("latitude")
      Double latitude,
      @Parameter(description = "사용자 경도", required = true, example = "126.570667")
      @RequestParam("longitude")
      Double longitude
  ) {
    List<IssueSummary> hot = issueService.getHotIssues();
    List<IssueSummary> all = issueService.getAllIssues();
    List<IssueSummary> geo = issueService.getGeoIssues(latitude, longitude);
    MainIssues mainIssues = new MainIssues(hot, geo, all);
    log.info("메인 이슈들 ={}", mainIssues);
    return ResponseEntity.status(HttpStatus.OK).body(mainIssues);
  }
}
