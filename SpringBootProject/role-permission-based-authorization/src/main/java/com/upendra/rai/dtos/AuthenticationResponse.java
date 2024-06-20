package com.upendra.rai.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upendra.rai.entities.Role;
import com.upendra.rai.entities.User;
import com.upendra.rai.entities.User.UserBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

  @JsonProperty("access_token")
  private String accessToken;
  
  @JsonProperty("refresh_token")
  private String refreshToken;
  
  private UserResponse user;
  
}
