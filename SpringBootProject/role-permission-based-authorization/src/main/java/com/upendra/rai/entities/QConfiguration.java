package com.upendra.rai.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConfiguration is a Querydsl query type for Configuration
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConfiguration extends EntityPathBase<Configuration> {

    private static final long serialVersionUID = 418652061L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConfiguration configuration = new QConfiguration("configuration");

    public final QBaseEntity _super;

    public final EnumPath<com.upendra.rai.enums.AccessLevel> accessLevel = createEnum("accessLevel", com.upendra.rai.enums.AccessLevel.class);
//
    public final SetPath<AppType, QAppType> appTypes = this.<AppType, QAppType>createSet("appTypes", AppType.class, QAppType.class, PathInits.DIRECT2);
//

    public final EnumPath<com.upendra.rai.enums.Category> category = createEnum("category", com.upendra.rai.enums.Category.class);

    public final StringPath code = createString("code");

    // inherited
    public final QUser createdBy;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdDate;

//    public final EnumPath<com.gt.madinaapps.client.service.apiclient.enums.DataType> dataType = createEnum("dataType", com.gt.madinaapps.client.service.apiclient.enums.DataType.class);

    public final StringPath defaultValue = createString("defaultValue");

    public final StringPath description = createString("description");

    public final NumberPath<Integer> displayOrder = createNumber("displayOrder", Integer.class);

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final BooleanPath isActive;

    public final BooleanPath isRequired = createBoolean("isRequired");

    public final BooleanPath isSuperAdminManaged = createBoolean("isSuperAdminManaged");

    public final StringPath label = createString("label");

    // inherited
    public final QUser lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> lastModifiedDate;

//    public final ListPath<ConfigurationOption, QConfigurationOption> options = this.<ConfigurationOption, QConfigurationOption>createList("options", ConfigurationOption.class, QConfigurationOption.class, PathInits.DIRECT2);

    public final StringPath placeholder = createString("placeholder");

    public final StringPath section = createString("section");

    //inherited
    public final StringPath uuid;

    public QConfiguration(String variable) {
        this(Configuration.class, forVariable(variable), INITS);
    }

    public QConfiguration(Path<? extends Configuration> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConfiguration(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConfiguration(PathMetadata metadata, PathInits inits) {
        this(Configuration.class, metadata, inits);
    }

    public QConfiguration(Class<? extends Configuration> type, PathMetadata metadata, PathInits inits) {
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

