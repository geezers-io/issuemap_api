package com.ex.befinal.issue.domain;

import java.util.List;

/**
 * 메인 이슈 게시글 리스트
 * <a href="https://www.figma.com/file/3intw018fJlvHekP4CUOO4/ISSUEMAP?type=design&node-id=24%3A627&mode=design&t=26Qyd7698ak5bCSh-1">페이지 링크</a>
 *
 * @param hot
 * @param geo
 * @param all
 */
public record MainIssues(List<IssueSummary> hot, List<IssueSummary> geo, List<IssueSummary> all) {}
