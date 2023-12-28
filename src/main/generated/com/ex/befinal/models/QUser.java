package com.ex.befinal.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1228874778L;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.util.Date> createAt = createDateTime("createAt", java.util.Date.class);

    public final DateTimePath<java.util.Date> disableAt = createDateTime("disableAt", java.util.Date.class);

    public final BooleanPath enable = createBoolean("enable");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath kakaoId = createString("kakaoId");

    public final StringPath nickName = createString("nickName");

    public final StringPath provider = createString("provider");

    public final DateTimePath<java.util.Date> removedAt = createDateTime("removedAt", java.util.Date.class);

    public final NumberPath<Short> reportCount = createNumber("reportCount", Short.class);

    public final EnumPath<com.ex.befinal.constant.UserRole> role = createEnum("role", com.ex.befinal.constant.UserRole.class);

    public final StringPath withdrawReason = createString("withdrawReason");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

