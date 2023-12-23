package com.minton.userservice.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ResetPasswordRequest {

   @NotEmpty
   private String email;
   @NotEmpty
   private String newPassword;

}
