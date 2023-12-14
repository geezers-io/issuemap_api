package com.ex.befinal.repository;

import static com.ex.befinal.models.QPost.post;

import com.ex.befinal.admin.dto.AdminAllIssuesResponse;
import com.ex.befinal.admin.dto.AdminIssuesResponse;
import com.ex.befinal.models.Post;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostDslRepository {
  private final JPAQueryFactory queryFactory;

  public List<AdminIssuesResponse> findAllByUserId(
      Long userId,
      Pageable pageable
  ) {
    return queryFactory
        .select(Projections.constructor(AdminIssuesResponse.class,
                post.id,
                post.title,
                post.createdAt))
        .from(post)
        .where(post.user.id.eq(userId))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();
  }


}
