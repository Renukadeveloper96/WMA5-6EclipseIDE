package com.upendra.rai.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppIconResponseDto {

  private Long id;
  private String iconName;
  private String iconPath;
  private String iconUrl;
  private Boolean isActive;
//  private AuditDto audit;

}
