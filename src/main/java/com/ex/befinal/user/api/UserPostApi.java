package com.ex.befinal.user.api;

import com.ex.befinal.admin.dto.AdminIssuesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "C. 작성 이슈 확인 API")
@RequestMapping("/userPost")
public class UserPostApi {
    @Operation(summary = "내가 작성한 게시글 리스트 API")
    public ResponseEntity<Page<UserIssuesResponse>> getUserIssues(
            @Parameter(description = "조회할 페이지 넘버(0부터 시작)", required = true)
            @RequestParam("page") int page,
            @Parameter(description = "가져올 데이터 갯수 단위", required = true)
            @RequestParam("size") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<AdminIssuesResponse> responses = adminService.getAdminUserIssues(pageable, nickname);
        return ResponseEntity.status(HttpStatus.OK).body(responses);

    }
}
