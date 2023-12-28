package com.ex.befinal.issue.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface HotIssueSummaryProjection {
  Long getId();

  String getTitle();

  String getThumbnailUrl();

  String getDescription();

  String getTags();

  Date getCreatedAt();

  Long getCount();
}