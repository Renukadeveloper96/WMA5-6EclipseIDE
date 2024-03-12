package com.studentcrud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="address")
@Entity
@Data
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String location;
	
	@OneToOne(targetEntity=Library.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable = false, name = "library_id")
	private Library library;
  
}
