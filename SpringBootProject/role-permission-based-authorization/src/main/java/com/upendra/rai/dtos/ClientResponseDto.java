package com.upendra.rai.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {
  private String clientId;
  private String uuid;
  private String code;
  private String name;
  private String logoUrl;
  private String street;
  private String city;
  private String zipcode;
  private String timeZone;
  private Integer timeZoneOffset;
  private String joinedTime;
  private String updatedTime;
  private String status;
  private String website;
  private String remark;
  private String alias;
}
