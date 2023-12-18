package com.ex.befinal.repository;

import static com.ex.befinal.models.QAttachment.attachment;
import static com.ex.befinal.models.QPost.post;
import static com.ex.befinal.models.QPostTag.postTag;
import static com.ex.befinal.models.QTag.tag;
import static com.querydsl.core.group.GroupBy.groupBy;
import static java.util.Collections.list;

import com.ex.befinal.admin.dto.AdminAllIssuesResponse;
import com.ex.befinal.admin.dto.AdminIssuesResponse;
import com.ex.befinal.issue.domain.IssueSummary;
import com.ex.befinal.issue.domain.QIssueSummary;
import com.ex.befinal.models.Post;
import com.ex.befinal.models.QAttachment;
import com.ex.befinal.models.QPost;
import com.ex.befinal.models.QPostTag;
import com.ex.befinal.models.QTag;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
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

  public List<IssueSummary> findAllIssues() {
    QPost post = QPost.post;
    QTag tag = QTag.tag;
    QPostTag postTag = QPostTag.postTag;
    QAttachment attachment = QAttachment.attachment;
    QAttachment subAttachment = QAttachment.attachment;
    List<IssueSummary> issueSummaries = groupBy(post.id).list(
        new QIssueSummary(
            post.id,
            post.title,
            subAttachment.uploadPath.coalesce("No Image"),
            post.description,
            GroupBy.set(tag.name),
            post.createdAt
        ))
        .transform(queryFactory
            .from(post)
            .leftJoin(postTag).on(postTag.post.id.eq(post.id))
            .leftJoin(tag).on(tag.id.eq(postTag.tag.id))
            .leftJoin(attachment).on(attachment.id.eq(
                getPostAttachExpression(attachment, post.id)))
            .where(post.removedAt.isNull().and(post.disabledAt.isNull())));
    return issueSummaries;
  }

  private JPQLQuery<Long> getPostAttachExpression(
      QAttachment attachment,
      NumberPath<Long> postId
  ) {
    return JPAExpressions.select(attachment.id.min())
        .from(attachment)
        .where(
            attachment.post.id.eq(postId)
        );
  }
}
