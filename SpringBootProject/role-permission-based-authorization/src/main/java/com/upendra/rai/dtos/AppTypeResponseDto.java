package com.upendra.rai.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.upendra.rai.entities.AppIcon;
import com.upendra.rai.entities.AppVersion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppTypeResponseDto {

  private Long appTypeId;
  private String appTypeLabel;
  private AppVersion appVersion;
  private String description;
  private String appTypeCode;
  private Boolean isActive;
}
