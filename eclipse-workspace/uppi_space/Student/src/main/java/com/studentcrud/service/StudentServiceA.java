package com.studentcrud.service;

import java.util.List;

import com.studentcrud.dto.StudentDtoA;
import com.studentcrud.dto.StudentRequestA;


public interface StudentServiceA {

	public StudentDtoA createStudenta(StudentRequestA studenta);
	public List<StudentDtoA> getAll();
	public StudentDtoA getById(Integer id);
	public StudentDtoA update(Integer id,StudentRequestA request);
	public String delete (Integer id);
}
