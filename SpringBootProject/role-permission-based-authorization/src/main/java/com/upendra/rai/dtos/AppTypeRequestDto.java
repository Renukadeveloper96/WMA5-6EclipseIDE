package com.upendra.rai.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppTypeRequestDto {

  @NotBlank(message = "NotBlank.appTypeRequestDto.appTypeLabel")
  @Size(max = 100,
      message = "Size.appTypeRequestDto.appTypeLabel(::)" + 100)
  private String appTypeLabel;

  @Size(max = 100,
      message = "Size.appTypeRequestDto.description(::)" + 100)
  private String description;

  @NotBlank(message = "NotBlank.appTypeRequestDto.appTypeCode")
  @Size(max = 100,
      message = "Size.appTypeRequestDto.appTypeCode(::)" + 100)
  private String appTypeCode;

}
