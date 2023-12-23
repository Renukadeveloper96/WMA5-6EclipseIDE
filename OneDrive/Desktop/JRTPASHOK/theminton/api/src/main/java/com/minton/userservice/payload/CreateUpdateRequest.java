package com.minton.userservice.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CreateUpdateRequest {
	
	private String firstName;

	private String lastName;

	@NotBlank
	@Email(regexp = ".+[@].+[\\.].+")
	private String email;

	private String provider;

}
