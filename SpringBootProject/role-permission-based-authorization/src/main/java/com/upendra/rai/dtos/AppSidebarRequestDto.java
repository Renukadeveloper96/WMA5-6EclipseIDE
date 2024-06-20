package com.upendra.rai.dtos;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppSidebarRequestDto {

  private Long id;

  private String code;

  private String label;

  private Boolean isActive;
}
