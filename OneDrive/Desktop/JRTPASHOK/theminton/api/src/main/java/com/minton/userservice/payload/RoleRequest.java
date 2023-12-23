package com.minton.userservice.payload;

import lombok.Data;


import javax.validation.constraints.*;

@Data
public class RoleRequest {

    @NotEmpty
    private String name;

    private String description;
}
