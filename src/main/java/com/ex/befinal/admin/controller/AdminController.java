package com.ex.befinal.admin.controller;

import com.ex.befinal.admin.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "E. 관리자 API")
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

  private final AdminService adminService;

//  @GetMapping
//  public ResponseEntity<Void>
//
//  //kakaoId + 닉네임, 작성한 이슈수, 작성한 댓글 수, 상태, 가입일자
//
//  @GetMapping
//  public ResponseEntity<Void>
//
//  //작성한 이슈목록으로 이동

  @Operation(summary = "관리자가 사용자 비/활성화 시키는 API")
  @PatchMapping("/disable/{kakaoId}")
  public ResponseEntity<Boolean> disableUser(
      @PathVariable String kakaoId
  ) {
    boolean status = adminService.disableUser(kakaoId);
    return ResponseEntity.status(HttpStatus.OK).body(status);

  }

  //사용자 비활성화
}
