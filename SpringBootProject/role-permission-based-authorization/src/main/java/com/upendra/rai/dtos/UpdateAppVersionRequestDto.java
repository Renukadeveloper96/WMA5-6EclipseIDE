package com.upendra.rai.dtos;


import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateAppVersionRequestDto {

  @NotBlank(message = "NotBlank.appVersionRequestDto.appTypeId")
  private String appTypeId;

  @NotBlank(message = "NotBlank.appVersionRequestDto.version")
  @Size(max = 100,
      message = "Size.appVersionRequestDto.version(::)" + 100)
  private String version;

  @NotBlank(message = "NotBlank.appVersionRequestDto.versionDate")
  private String versionDate;

  @Size(max = 100,
      message = "Size.appVersionRequestDto.description(::)" + 100)
  private String description;

  private MultipartFile appApk;

}
