package com.upendra.rai.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusinessCategoryRequestDto {

  @NotBlank(message = "NotBlank.businessCategoryRequestDto.category")
  @Size(max = 100,
      message = "Size.businessCategoryRequestDto.category(::)"
          + 100)
  private String category;

  @Size(max = 100,
      message = "Size.businessCategoryRequestDto.iconImagePath(::)"
          + 100)
  private String iconImagePath;

}
