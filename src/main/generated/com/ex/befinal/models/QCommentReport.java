package com.ex.befinal.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentReport is a Querydsl query type for CommentReport
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentReport extends EntityPathBase<CommentReport> {

    private static final long serialVersionUID = -662721864L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentReport commentReport = new QCommentReport("commentReport");

    public final DateTimePath<java.util.Date> createAt = createDateTime("createAt", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath reason = createString("reason");

    public final QUser reporterId;

    public final QComment targetCommentId;

    public QCommentReport(String variable) {
        this(CommentReport.class, forVariable(variable), INITS);
    }

    public QCommentReport(Path<? extends CommentReport> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentReport(PathMetadata metadata, PathInits inits) {
        this(CommentReport.class, metadata, inits);
    }

    public QCommentReport(Class<? extends CommentReport> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reporterId = inits.isInitialized("reporterId") ? new QUser(forProperty("reporterId")) : null;
        this.targetCommentId = inits.isInitialized("targetCommentId") ? new QComment(forProperty("targetCommentId"), inits.get("targetCommentId")) : null;
    }

}

