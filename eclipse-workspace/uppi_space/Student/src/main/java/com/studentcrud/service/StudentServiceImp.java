package com.studentcrud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.studentcrud.dto.StudentDto;
import com.studentcrud.dto.StudentRequest;
import com.studentcrud.entity.Student;
import com.studentcrud.repo.StudentRepo;

@Service
public class StudentServiceImp implements StudentService {
	@Autowired
	private StudentRepo studentRepo;
	
	Logger logger = LoggerFactory.getLogger(StudentServiceImp.class);
	@Override
	public List<StudentDto> getStudent() {
		List<Student> students=studentRepo.findAll();
		return students.stream().map(st->{
		StudentDto dto=new StudentDto();
		dto.setId(st.getId());
		dto.setName(st.getName());
		dto.setNumber(st.getNumber());
		dto.setAbout(st.getAbout());
		dto.setEmail(st.getEmail());
		return dto;
		}).collect(Collectors.toList());
		}

	@Override
	public StudentDto createStudent(StudentRequest studentRequest) {//request to entity;
		
		Student st=new Student();
		st.setAbout(studentRequest.getAbout());
		st.setEmail(studentRequest.getEmail());
		st.setName(studentRequest.getName());
		st.setNumber(studentRequest.getNumber());
		studentRepo.save(st);
	
		//entity to dto
		StudentDto dto=new StudentDto();
		dto.setId(st.getId());
		dto.setEmail(st.getEmail());
		dto.setAbout(st.getAbout());
		dto.setName(st.getName());
		dto.setNumber(st.getNumber());
		return dto;
		
	}

	@Override
	public StudentDto getStudentById(Integer id) {
		
		Student st=studentRepo.findById(id).get();
		StudentDto dto=new StudentDto();
		dto.setId(st.getId());
		dto.setAbout(st.getAbout());
		dto.setEmail(st.getEmail());
		dto.setName(st.getName());
		dto.setNumber(st.getNumber());
		return dto;	
	}
	@Override
	public StudentDto update(Integer id, StudentRequest studentRequest) {//request to ENTITY
		Student student=studentRepo.findById(id).get();
		student.setAbout(studentRequest.getAbout());
		student.setEmail(studentRequest.getEmail());
		student.setName(studentRequest.getName());
		student.setNumber(studentRequest.getNumber());
		studentRepo.save(student);
		
		StudentDto dto=new StudentDto();
		dto.setAbout(student.getAbout());
		dto.setEmail(student.getEmail());
		dto.setName(student.getName());
		dto.setNumber(student.getNumber());
		dto.setId(student.getId());
		return dto;	
	}

	@Override
	public String delete(Integer id) {
		Student student=studentRepo.findById(id).get();
	    studentRepo.delete(student);
	    return "deleted";
//		studentRepo.deleteById(id);
//		return "deleted"+id;
	}
}
