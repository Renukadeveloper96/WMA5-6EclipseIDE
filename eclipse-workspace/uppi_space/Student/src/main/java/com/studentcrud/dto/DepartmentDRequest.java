package com.studentcrud.dto;

import lombok.Data;

@Data
public class DepartmentDRequest {
	private int id;
	private String departmentName;
	private String departmentAddress;
	private String departmentCode;
}
