package com.ex.befinal.admin.controller;

import com.ex.befinal.admin.dto.AdminAllIssuesResponse;
import com.ex.befinal.admin.dto.AdminIssuesResponse;
import com.ex.befinal.admin.dto.RecentRegisteredUser;
import com.ex.befinal.admin.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "E. 관리자 API")
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

  private final AdminService adminService;

  @Operation(summary = "모든 사용자 게시글 리스트 가져오기 API")
  @GetMapping("/issues/all")
  public ResponseEntity<Page<AdminAllIssuesResponse>> getAdminAllIssues(
      @Parameter(description = "조회할 페이지 넘버(0부터 시작)", required = true)
      @RequestParam("page") int page,
      @Parameter(description = "가져올 데이터 갯수 단위", required = true)
      @RequestParam("size") int size
  ) {
    PageRequest pageable = PageRequest.of(page, size);
    Page<AdminAllIssuesResponse> responses = adminService.getAdminAllIssues(pageable);
    return ResponseEntity.status(HttpStatus.OK).body(responses);
  }

  @Operation(summary = "특정 사용자가 작성한 게시글 리스트 API")
  @GetMapping("/issues")
  public ResponseEntity<Page<AdminIssuesResponse>> getAdminIssues(
      @Parameter(description = "사용자 닉네임", required = true)
      @RequestParam("nickname") String nickname,
      @Parameter(description = "조회할 페이지 넘버(0부터 시작)", required = true)
      @RequestParam("page") int page,
      @Parameter(description = "가져올 데이터 갯수 단위", required = true)
      @RequestParam("size") int size
  ) {
    PageRequest pageable = PageRequest.of(page, size);
    Page<AdminIssuesResponse> responses = adminService.getAdminUserIssues(pageable, nickname);
    return ResponseEntity.status(HttpStatus.OK).body(responses);
  }

  @Operation(summary = "관리자가 사용자 비/활성화 시키는 API")
  @PatchMapping("/disable/{nickname}")
  public ResponseEntity<Boolean> disableUser(
      @PathVariable String nickname
  ) {
    boolean status = adminService.disableUser(nickname);
    return ResponseEntity.status(HttpStatus.OK).body(status);

  }

  @Operation(summary = "최근 회원가입 내역")
  @GetMapping("/recent")
  public ResponseEntity<Page<RecentRegisteredUser>> getRecentRegisteredUser(
      @Parameter(description = "조회할 페이지 넘버(0부터 시작)", required = true)
      @RequestParam("page") int page,
      @Parameter(description = "가져올 데이터 갯수 단위", required = true)
      @RequestParam("size") int size
  ) {
    PageRequest pageable = PageRequest.of(page, size);
    Page<RecentRegisteredUser> recentRegisteredUser =
        adminService.getRecentRegisteredUser(pageable);
    return ResponseEntity.status(HttpStatus.OK).body(recentRegisteredUser);
  }


}
