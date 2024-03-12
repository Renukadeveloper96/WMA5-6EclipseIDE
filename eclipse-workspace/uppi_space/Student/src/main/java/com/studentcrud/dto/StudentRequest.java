package com.studentcrud.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class StudentRequest {
	private Integer id;	
	private String email;
	private String about;
	private String name;
	private Integer number;
}
