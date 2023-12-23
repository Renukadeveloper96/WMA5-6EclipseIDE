package com.minton.userservice.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ResetNewPasswordRequest {

    @NotEmpty
    private String token;
    @NotEmpty
    private String password;

}
