package com.studentcrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studentcrud.dto.StudentDto;
import com.studentcrud.dto.StudentRequest;
import com.studentcrud.entity.Student;

@Service
public interface StudentService {

	public List<StudentDto>getStudent();
	public StudentDto createStudent(StudentRequest studentRequest);
	public StudentDto getStudentById(Integer id);
    public StudentDto update(Integer id,StudentRequest studentRequest);
    public String delete(Integer id);
}
