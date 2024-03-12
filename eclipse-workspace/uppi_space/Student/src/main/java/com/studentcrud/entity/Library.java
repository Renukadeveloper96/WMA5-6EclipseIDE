package com.studentcrud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Table(name="library")
@Entity
@Data
public class Library {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;

	@OneToOne(targetEntity=Address.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable = false, name = "address_id")
	private Address address;
	
}
