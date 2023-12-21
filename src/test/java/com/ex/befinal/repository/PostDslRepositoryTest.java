package com.ex.befinal.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.ex.befinal.constant.LikeStatus;
import com.ex.befinal.models.QLikeAndDislike;
import com.querydsl.core.types.dsl.NumberExpression;
import groovy.util.logging.Slf4j;
import java.util.Date;
import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.Test;

@Slf4j
class PostDslRepositoryTest {

  @Test
  public void lem() {
    LocalDateTime now = new LocalDateTime();
    LocalDateTime threeMonths = now.plusMonths(3);
    System.out.println("now = " + now);
    System.out.println("threeMonthsAgo = " + threeMonths);
    Date startDate = now.toDate();
    System.out.println("startDate = " + startDate);
    Date endDate = threeMonths.toDate();
    System.out.println("endDate = " + endDate);

  }
  
  @Test
  public void dsl() {
    QLikeAndDislike likeAndDislike = QLikeAndDislike.likeAndDislike;
    NumberExpression<Long> likeCount = likeAndDislike.status.eq(LikeStatus.LIKE).count();
    System.out.println("likeCount = " + likeCount);
  }

}