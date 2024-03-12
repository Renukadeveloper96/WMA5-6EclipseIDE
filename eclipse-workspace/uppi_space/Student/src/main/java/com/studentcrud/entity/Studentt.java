package com.studentcrud.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studentcrud.onetooneentity.Subject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Studentt {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy="enrolledStudents")
	private Set<Subject>subjects=new HashSet<>();
	
	
}
