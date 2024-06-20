package com.upendra.rai.dtos;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.querydsl.codegen.EntityType;

import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientConfigurationRequestDto {

  private String configurationId;
  private Object value;
  private EntityType entityType;
  private String entityId;

  @AssertTrue(message = "NotEmpty.clientConfigurationRequestDto.entityType.entityId")
  private boolean isEntityType() {
    return (StringUtils.isBlank(entityId) && Objects.isNull(entityType))
        || (StringUtils.isNotBlank(entityId) && Objects.nonNull(entityType));
  }

}
