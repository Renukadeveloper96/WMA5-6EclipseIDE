package com.upendra.rai.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAudit is a Querydsl query type for Audit
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QAudit extends EntityPathBase<Audit> {

    private static final long serialVersionUID = -814031486L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAudit audit = new QAudit("audit");

    public final QUser createdBy;

    public final DateTimePath<java.time.ZonedDateTime> createdDate = createDateTime("createdDate", java.time.ZonedDateTime.class);

    public final QUser lastModifiedBy;

    public final DateTimePath<java.time.ZonedDateTime> lastModifiedDate = createDateTime("lastModifiedDate", java.time.ZonedDateTime.class);

    public QAudit(String variable) {
        this(Audit.class, forVariable(variable), INITS);
    }

    public QAudit(Path<? extends Audit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAudit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAudit(PathMetadata metadata, PathInits inits) {
        this(Audit.class, metadata, inits);
    }

    public QAudit(Class<? extends Audit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new QUser(forProperty("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new QUser(forProperty("lastModifiedBy")) : null;
    }

}

