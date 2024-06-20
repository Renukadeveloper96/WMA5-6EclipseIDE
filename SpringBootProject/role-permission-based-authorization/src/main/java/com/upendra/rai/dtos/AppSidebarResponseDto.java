package com.upendra.rai.dtos;

import com.upendra.rai.entities.AppIcon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppSidebarResponseDto {

  private Long id;
  private AppIcon appIcon;
  private String code;
  private String label;
  private Integer sortOrder;
  private Boolean isActive;
}
