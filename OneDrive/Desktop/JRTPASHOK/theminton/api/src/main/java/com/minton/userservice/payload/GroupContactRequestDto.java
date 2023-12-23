package com.minton.userservice.payload;

import lombok.Data;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;

@Data
public class GroupContactRequestDto {

	private long value;

	private String groupName;

	private Set<Long> contactIds;

}
