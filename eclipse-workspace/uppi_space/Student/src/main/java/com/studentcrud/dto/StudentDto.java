package com.studentcrud.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class StudentDto {
	private Integer id;	
	private String email;
	private String about;
	private String name;
	private Integer number;
}
