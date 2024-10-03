package com.blogrenukapp.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
	private Long id;
	
	@NotEmpty
	@Size(min=4,message="username must be min of 4 characters !!")
	private String name;
	
	@Email(message="Email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=10,message="password must be min of 3 characters and max of 10 char!!")
	private String password;
	
	@NotNull
	private String about;
}
