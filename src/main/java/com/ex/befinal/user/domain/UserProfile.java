package com.ex.befinal.user.domain;

/**
 * 마이페이지 사용자 데이터
 * <a href="https://www.figma.com/file/3intw018fJlvHekP4CUOO4/ISSUEMAP?type=design&node-id=102%3A2013&mode=design&t=26Qyd7698ak5bCSh-1">페이지 링크</a>
 *
 * @param nickname
 * @param issueCount 작성한 이슈
 * @param commentCount 작성한 댓글
 */
public record UserProfile(String nickname, long issueCount, long commentCount) {}
