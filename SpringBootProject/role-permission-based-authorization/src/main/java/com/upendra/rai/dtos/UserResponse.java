package com.upendra.rai.dtos;

import com.upendra.rai.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class UserResponse {
private Integer id;
private String firstname;
private String lastname;
private String email;
private Role role;


}
