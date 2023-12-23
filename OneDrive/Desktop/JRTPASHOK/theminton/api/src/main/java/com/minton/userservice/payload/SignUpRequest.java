package com.minton.userservice.payload;


import lombok.Data;

import javax.validation.constraints.*;


@Data
public class SignUpRequest {


    private String name;

    private String firstName;

    private String lastName;

    private String phoneNumber;


    @NotBlank
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;

    @NotBlank
    private String password;



}
