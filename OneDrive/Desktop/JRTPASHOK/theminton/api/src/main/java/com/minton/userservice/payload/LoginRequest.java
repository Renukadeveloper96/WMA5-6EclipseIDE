package com.minton.userservice.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
public class LoginRequest {
    @NotBlank
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;

    @NotBlank
    private String password;


}
