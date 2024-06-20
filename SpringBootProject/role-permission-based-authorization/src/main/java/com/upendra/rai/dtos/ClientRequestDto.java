package com.upendra.rai.dtos;


import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientRequestDto {

	  private Long id;
	  private String uuid;
	  
  @NotBlank(message = "NotBlank.clientRequestDto.name")
  @Size(max = 100,
      message = "Size.clientRequestDto.name(::)" + 100)
  private String name;

  private String logoUrl;
  @NotBlank(message = "NotBlank.clientRequestDto.street")
  @Size(max = 100,
      message = "Size.clientRequestDto.street(::)" + 100)
  private String street;

  @NotBlank(message = "NotBlank.clientRequestDto.city")
  @Size(max = 100,
      message = "Size.clientRequestDto.city(::)" + 100)
  private String city;

  @NotBlank(message = "NotBlank.clientRequestDto.zipcode")
  @Size(max = 100,
      message = "Size.clientRequestDto.zipcode(::)" + 100)
  private String zipcode;

  @Size(max = 100,
      message = "Size.clientRequestDto.website(::)" + 100)
  private String website;

  @Size(max = 100,
      message = "Size.clientRequestDto.alias(::)" + 100)
  private String alias;

  @Size(max = 100,
      message = "Size.clientRequestDto.apiSecret(::)" + 100)
  private String apiSecret;

  @Size(max = 100,
      message = "Size.clientRequestDto.remark(::)" + 100)
  private String remark;



}
