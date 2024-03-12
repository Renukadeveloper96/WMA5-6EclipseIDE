package com.studentcrud.dto;

import lombok.Data;

@Data
public class EmployeeBRequest {

	private Integer id;
	
	private String emp_name;
	private double emp_salary;
}
