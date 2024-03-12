package com.studentcrud.dto;

import lombok.Data;

@Data
public class StudentRequestA {

	private Integer id;
	private String stdName;
	
	private Long mob;
	
	private String classMode;
	
	private String courseName;
	
	private String enqStatus;
}
