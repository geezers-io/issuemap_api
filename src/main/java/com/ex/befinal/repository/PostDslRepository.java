package com.ex.befinal.repository;

import static com.ex.befinal.models.QLikeAndDislike.likeAndDislike;
import static com.ex.befinal.models.QPost.post;
import static com.querydsl.core.group.GroupBy.groupBy;

import com.ex.befinal.admin.dto.AdminIssuesResponse;
import com.ex.befinal.constant.LikeStatus;
import com.ex.befinal.issue.domain.IssueSummary;
import com.ex.befinal.issue.domain.QIssueSummary;
import com.ex.befinal.models.QAttachment;
import com.ex.befinal.models.QLikeAndDislike;
import com.ex.befinal.models.QPost;
import com.ex.befinal.models.QPostTag;
import com.ex.befinal.models.QTag;
import com.querydsl.core.Tuple;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Pageable;
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
        .orderBy(post.createdAt.desc())
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
            .where(post.removedAt.isNull().and(post.disabledAt.isNull()))
            .groupBy(
                post.id,
                post.title,
                subAttachment.uploadPath,
                post.description,
                tag.name,
                post.createdAt)
            .orderBy(post.createdAt.desc()));
    return issueSummaries;
  }

  private JPQLQuery<Long> getPostAttachExpression(
      QAttachment attachment,
      NumberPath<Long> postId
  ) {
    return JPAExpressions
        .select(attachment.id.min())
        .from(attachment)
        .where(attachment.post.id.eq(postId));
  }

  public JPQLQuery<Long> getPostIdExpression(
      QPost post,
      QLikeAndDislike likeAndDislike
  ) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime threeMonthsAgo = now.minusMonths(3);
    Date startDate = now.toDate();
    Date endDate = threeMonthsAgo.toDate();

    NumberExpression<Long> likeCount = likeAndDislike.id.count();

    return JPAExpressions
        .select(
            likeAndDislike.post.id
        )
        .from(post)
        .leftJoin(likeAndDislike).on(
            post.id.eq(likeAndDislike.post.id),
            likeAndDislike.status.eq(LikeStatus.LIKE)
        )
        .where(
            post.createdAt.between(startDate, endDate)
        )
        .groupBy(
            post.id
        )
        .orderBy(
            likeCount.desc()
        )
        .limit(20
        );
  }

  public List<Tuple> getPostIdAndCount() {

    List<Tuple> result = queryFactory
        .select(
            likeAndDislike.post.id.count(),
            likeAndDislike.post.id
        )
        .from(likeAndDislike)
        .where(likeAndDislike.status.eq(LikeStatus.LIKE))
        .groupBy(likeAndDislike.post.id)
        .orderBy(likeAndDislike.post.id.count().desc()).limit(20).fetch();
    return result;
  }


  public List<IssueSummary> findHotIssues(

  ) {
    QPost post = QPost.post;
    QTag tag = QTag.tag;
    QPostTag postTag = QPostTag.postTag;
    QAttachment attachment = QAttachment.attachment;
    QAttachment subAttachment = QAttachment.attachment;
    QLikeAndDislike likeAndDislike = QLikeAndDislike.likeAndDislike;

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
            .leftJoin(likeAndDislike).on(likeAndDislike.post.id.eq(
                getPostIdExpression(post, likeAndDislike)))
            .where(post.removedAt.isNull().and(post.disabledAt.isNull())));
    return issueSummaries;

  }

  public List<IssueSummary> findGeoIssues(Double latitude, Double longitude) {
    return null;
  }
}
