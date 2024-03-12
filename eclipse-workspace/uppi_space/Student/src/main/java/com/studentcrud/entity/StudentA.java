package com.studentcrud.entity;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="studenta")
@Data
public class StudentA {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;	
	
	private String stdName;
	
	private Long mob;
	
	private String classMode;
	
	private String courseName;
	
	private String enqStatus;
	
	@CreationTimestamp
	private LocalDate dateCreated;
	
	@UpdateTimestamp
	private LocalDate lastUpdate;
}
