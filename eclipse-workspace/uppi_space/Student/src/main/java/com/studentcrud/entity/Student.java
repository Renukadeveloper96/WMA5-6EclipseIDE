package com.studentcrud.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="student")
@Data
public class Student {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;	
	private String email;
	private String about;
	private String name;
	private Integer number;
	
}
