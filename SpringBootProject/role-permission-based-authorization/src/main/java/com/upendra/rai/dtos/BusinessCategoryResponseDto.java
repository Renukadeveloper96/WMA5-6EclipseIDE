package com.upendra.rai.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.upendra.rai.entities.Client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessCategoryResponseDto {

  private Long businessCategoryId;
  private String category;
  private Client client;
  private String iconImagePath;
  private String iconImagePathUrl;
  private Integer sortOrder;
  private Boolean isActive;

}
