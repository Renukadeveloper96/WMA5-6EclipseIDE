package com.upendra.rai.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAppType is a Querydsl query type for AppType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAppType extends EntityPathBase<AppType> {

    private static final long serialVersionUID = -732890910L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAppType appType = new QAppType("appType");

    public final QBaseEntity _super;

    public final StringPath appTypeCode = createString("appTypeCode");

    public final StringPath appTypeLabel = createString("appTypeLabel");

//    public final SetPath<AppVersion, QAppVersion> appVersions = this.<AppVersion, QAppVersion>createSet("appVersions", AppVersion.class, QAppVersion.class, PathInits.DIRECT2);

    public final SetPath<Configuration, QConfiguration> configurations = this.<Configuration, QConfiguration>createSet("configurations", Configuration.class, QConfiguration.class, PathInits.DIRECT2);

    // inherited
    public final QUser createdBy;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdDate;

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final BooleanPath isActive;

    // inherited
    public final QUser lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> lastModifiedDate;

    //inherited
    public final StringPath uuid;

    public QAppType(String variable) {
        this(AppType.class, forVariable(variable), INITS);
    }

    public QAppType(Path<? extends AppType> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAppType(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAppType(PathMetadata metadata, PathInits inits) {
        this(AppType.class, metadata, inits);
    }

    public QAppType(Class<? extends AppType> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QBaseEntity(type, metadata, inits);
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.id = _super.id;
        this.isActive = _super.isActive;
        this.lastModifiedBy = _super.lastModifiedBy;
        this.lastModifiedDate = _super.lastModifiedDate;
        this.uuid = _super.uuid;
    }

}

