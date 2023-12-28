package com.ex.befinal.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAlert is a Querydsl query type for UserAlert
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAlert extends EntityPathBase<UserAlert> {

    private static final long serialVersionUID = -1026474730L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAlert userAlert = new QUserAlert("userAlert");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath read = createBoolean("read");

    public final QUser user;

    public QUserAlert(String variable) {
        this(UserAlert.class, forVariable(variable), INITS);
    }

    public QUserAlert(Path<? extends UserAlert> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAlert(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAlert(PathMetadata metadata, PathInits inits) {
        this(UserAlert.class, metadata, inits);
    }

    public QUserAlert(Class<? extends UserAlert> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

