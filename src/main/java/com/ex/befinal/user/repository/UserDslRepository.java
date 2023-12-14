package com.ex.befinal.user.repository;

import static com.ex.befinal.models.QUser.user;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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
}
