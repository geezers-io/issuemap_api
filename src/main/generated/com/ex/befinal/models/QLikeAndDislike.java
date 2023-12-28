package com.ex.befinal.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeAndDislike is a Querydsl query type for LikeAndDislike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikeAndDislike extends EntityPathBase<LikeAndDislike> {

    private static final long serialVersionUID = 614789280L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeAndDislike likeAndDislike = new QLikeAndDislike("likeAndDislike");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPost post;

    public final EnumPath<com.ex.befinal.constant.LikeStatus> status = createEnum("status", com.ex.befinal.constant.LikeStatus.class);

    public final QUser user;

    public QLikeAndDislike(String variable) {
        this(LikeAndDislike.class, forVariable(variable), INITS);
    }

    public QLikeAndDislike(Path<? extends LikeAndDislike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeAndDislike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeAndDislike(PathMetadata metadata, PathInits inits) {
        this(LikeAndDislike.class, metadata, inits);
    }

    public QLikeAndDislike(Class<? extends LikeAndDislike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

