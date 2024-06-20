package com.upendra.rai.dtos;

import java.util.List;

import com.querydsl.core.util.CollectionUtils;
import com.upendra.rai.enums.AccessLevel;
import com.upendra.rai.enums.Category;
import com.upendra.rai.enums.DataType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConfigurationRequestDto {

  @NotBlank(message = "NotBlank.configurationRequestDto.code")
  private String code;

  @NotBlank(message = "NotBlank.configurationRequestDto.label")
  private String label;

  private String placeholder;

  private String description;

  private Object defaultValue;

  @NotNull(message = "NotNull.configurationRequestDto.isSuperAdminManaged")
  private Boolean isSuperAdminManaged;

  @NotNull(message = "NotNull.configurationRequestDto.fileAccess")
  private AccessLevel accessLevel;

  @NotNull(message = "NotNull.configurationRequestDto.dataType")
  private DataType dataType;

  @NotNull(message = "NotNull.configurationRequestDto.category")
  private Category category;

  private Boolean isRequired;
  
}
