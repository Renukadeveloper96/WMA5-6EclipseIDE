package com.studentcrud.onetooneentity;

import java.util.*;
import java.util.Set;

import com.studentcrud.entity.Studentt;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Subject {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	 Long id;
	
	private String name;
	@ManyToMany
	@JoinTable(
			name="student_enrolled",
			joinColumns=@JoinColumn(name="subject_id"),
			inverseJoinColumns=@JoinColumn(name="student_id")
			)
	Set<Studentt>enrolledStudents=new HashSet<>();	
	
	@ManyToOne
	@JoinColumn(name = "teacher_id", referencedColumnName = "id")
	private Teacher teacher;
	
}
