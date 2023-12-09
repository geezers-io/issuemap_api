package com.ex.befinal.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum GlobalException implements BasicErrorCase {
  ILLEGAL_ARGUMENT(HttpStatus.BAD_REQUEST, "FAULT_ARG", "잘못된 인자 값입니다."),
  SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER_ERROR", "서버 에러가 발생하였습니다."),
  BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "잘못된 요청입니다."),
  ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "ALREADY_EXISTS", "이미 존재하는 자원입니다."),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "인증이 유효하지 않습니다."),
  FORBIDDEN(HttpStatus.FORBIDDEN, "FORBIDDEN", "해당 자원에 대해 요청 및 접근 권한이 없습니다."),
  NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND_RESOURCE", "해당 자원을 찾을 수 없습니다.");

  private final HttpStatus httpStatus;
  private final String errorCode;
  private final String message;

  GlobalException(HttpStatus httpStatus, String errorCode, String message) {
    this.httpStatus = httpStatus;
    this.errorCode = errorCode;
    this.message = message;
  }

  @Override
  public BasicException create() {
    BasicErrorResponse response = BasicErrorResponse.builder()
        .httpStatus(httpStatus)
        .errorCode(errorCode)
        .message(message)
        .build();
    return new BasicException(response);
  }

}
