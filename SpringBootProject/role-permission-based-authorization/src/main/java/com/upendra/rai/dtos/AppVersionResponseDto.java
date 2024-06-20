package com.upendra.rai.dtos;

import com.upendra.rai.entities.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppVersionResponseDto extends BaseEntity{
  private String version;
//  private String versionDate;
  private String description;
//  private Boolean isDefault;
  private Boolean isActive;
}
