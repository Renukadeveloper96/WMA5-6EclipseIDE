package com.minton.userservice.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserTypeRequest {

    @Pattern(regexp="Free|Premium", message = "name should be either Free or Premium")
    @NotEmpty
    private String name;


}
