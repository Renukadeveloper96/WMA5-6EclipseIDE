package com.minton.userservice.payload;

import lombok.Data;

@Data
public class EngagementRequest {

	private String name;

	private Long engagementTypeId;

	private Long templateTypeId;

}
