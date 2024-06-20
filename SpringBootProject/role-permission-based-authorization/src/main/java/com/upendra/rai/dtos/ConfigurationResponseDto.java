package com.upendra.rai.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.upendra.rai.enums.AccessLevel;
import com.upendra.rai.enums.Category;
import com.upendra.rai.enums.DataType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurationResponseDto {

  private String configurationId;
  private String code;
  private String label;
  private String placeholder;
  private String description;
  private Object defaultValue;
  private Boolean isSuperAdminManaged;
  private Category category;
  private Integer displayOrder;
  private Boolean isActive;
  private AccessLevel accessLevel;
  private DataType dataType;
  private Boolean isRequired;

}
