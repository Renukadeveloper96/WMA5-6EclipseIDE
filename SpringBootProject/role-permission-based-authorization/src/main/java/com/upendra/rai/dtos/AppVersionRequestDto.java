package com.upendra.rai.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppVersionRequestDto {

  @NotBlank(message = "NotBlank.appVersionRequestDto.version")
  @Size(max = 100,
      message = "Size.appVersionRequestDto.version(::)" + 100)
  private String version;

  @Size(max = 100,
      message = "Size.appVersionRequestDto.description(::)" + 100)
  private String description;
  
  private Boolean isActive;

}
