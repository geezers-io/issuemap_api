package com.ex.befinal.user.repository;

import static com.ex.befinal.models.QUser.user;

import com.ex.befinal.admin.dto.SimpleUser;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDslRepository {
  private final JPAQueryFactory queryFactory;

  public Optional<Long> findByUserNickname(
      String nickname
  ) {
    return Optional.ofNullable(
        queryFactory
            .select(user.id)
            .from(user)
            .where(user.nickName.eq(nickname))
            .fetchOne()
    );
  }

  public List<Long> getAllIds(PageRequest pageable) {
    return queryFactory
        .select(user.id)
        .from(user)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();
  }

  public Optional<SimpleUser> findUserById(Long id) {
    return Optional.ofNullable(
        queryFactory
            .select(Projections.constructor(SimpleUser.class,
                user.nickName,
                user.enable,
                user.createAt))
            .from(user)
            .where(user.id.eq(id))
            .fetchOne()
    );
  }
}
