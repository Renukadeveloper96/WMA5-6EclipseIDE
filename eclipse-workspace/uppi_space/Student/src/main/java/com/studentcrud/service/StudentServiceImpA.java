package com.studentcrud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentcrud.dto.StudentDto;
import com.studentcrud.dto.StudentDtoA;
import com.studentcrud.dto.StudentRequest;
import com.studentcrud.dto.StudentRequestA;
import com.studentcrud.entity.StudentA;
import com.studentcrud.repo.StudentRepoA;

@Service
public class StudentServiceImpA implements StudentServiceA{

	@Autowired
	private StudentRepoA studentRepoA;
	
	@Override
	
	public StudentDtoA createStudenta(StudentRequestA studentRequest) {//request to entity;
		StudentA entity=new StudentA();
		entity.setStdName(studentRequest.getStdName());
		entity.setMob(studentRequest.getMob());
		entity.setClassMode(studentRequest.getClassMode());
		entity.setCourseName(studentRequest.getCourseName());
		entity.setEnqStatus(studentRequest.getEnqStatus());
		studentRepoA.save(entity);
		
		//entity to dto
		StudentDtoA dto=new StudentDtoA();
		dto.setId(entity.getId());
		dto.setStdName(entity.getStdName());
		dto.setMob(entity.getMob());
		dto.setClassMode(entity.getClassMode());
		dto.setCourseName(entity.getCourseName());
		dto.setEnqStatus(entity.getEnqStatus());
		return dto;
	}
	@Override
	public List<StudentDtoA> getAll() {
		List<StudentA>students=studentRepoA.findAll();
		return students.stream().map(st->{
			StudentDtoA dto=new StudentDtoA();
			dto.setId(st.getId());
			dto.setCourseName(st.getCourseName());
			dto.setClassMode(st.getClassMode());
			dto.setEnqStatus(st.getEnqStatus());
			dto.setMob(st.getMob());
			dto.setStdName(st.getStdName());
			return dto;
		}).collect(Collectors.toList());
	}
	@Override
	public StudentDtoA getById(Integer id) {
		StudentA studenta=studentRepoA.findById(id).get();
		StudentDtoA dto=new StudentDtoA();
		dto.setId(studenta.getId());
		dto.setClassMode(studenta.getClassMode());
		dto.setCourseName(studenta.getCourseName());
		dto.setEnqStatus(studenta.getEnqStatus());
		dto.setMob(studenta.getMob());
		dto.setStdName(studenta.getStdName());
		return dto;
	}
	@Override
	public StudentDtoA update(Integer id, StudentRequestA request) {//request to entity
		StudentA entity=studentRepoA.findById(id).get();
		entity.setStdName(request.getStdName());
		entity.setCourseName(request.getCourseName());
		entity.setClassMode(request.getClassMode());
		entity.setEnqStatus(request.getEnqStatus());
		entity.setMob(request.getMob());
		studentRepoA.save(entity);
		
		StudentDtoA dto=new StudentDtoA();
		dto.setId(entity.getId());
		dto.setStdName(entity.getStdName());
		dto.setCourseName(entity.getCourseName());
		dto.setClassMode(entity.getClassMode());
		dto.setEnqStatus(entity.getEnqStatus());
		dto.setMob(entity.getMob());
		return dto;
	}
	@Override
	public String delete(Integer id) {
		StudentA student= studentRepoA.findById(id).get();
		studentRepoA.delete(student);
		return "deleted";
	}

	
}
