package com.minton.userservice.payload;

import lombok.Data;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;

@Data
public class GroupRequestDto {

	private String groupName;
	
	private String createdBy;

	private String modifiedBy;

}
