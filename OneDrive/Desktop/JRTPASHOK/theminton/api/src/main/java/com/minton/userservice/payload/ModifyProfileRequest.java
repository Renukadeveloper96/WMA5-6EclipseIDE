package com.minton.userservice.payload;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Valid
public class ModifyProfileRequest {

    @NotEmpty
    @Size(max = 15, message = "firstName more than 15 chars")
    private String firstName;
    @NotEmpty
    @Size(max = 15, message = "lastName more than 15 chars")
    private String lastName;
    //@Size(max = 10, message = "phoneNumber more than 10 digits")
    private String phoneNumber;
    @Pattern(regexp="Free|Premium", message = "userType should be either Free or Premium")
    private String userType;

    @NotEmpty
    @Pattern(regexp="ADMIN|USER", message = "role should be either ADMIN or USER")
    private String role;

}
