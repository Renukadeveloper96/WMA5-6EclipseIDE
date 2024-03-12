package com.studentcrud.controller;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentcrud.dto.BaseResponseDTO;
import com.studentcrud.dto.StudentDto;
import com.studentcrud.dto.StudentRequest;
import com.studentcrud.entity.Student;
import com.studentcrud.service.StudentService;

@RestController
public class StudentController {
	
@Autowired
private StudentService studentService;
Logger logger = LoggerFactory.getLogger(StudentController.class);
@GetMapping("/students")
public List<StudentDto>getAllStudent(){
	return studentService.getStudent();
}

@PostMapping("/students")
public ResponseEntity<?> createStudent(@RequestBody StudentRequest studentRequest) {
	StudentDto studentDto= studentService.createStudent(studentRequest);
	if(studentDto!=null) {
		logger.info("result of create Student is executed");
		return ResponseEntity.ok(studentService.createStudent(studentRequest));
	}else {
		logger.error("create students list");
		return ResponseEntity.badRequest().body("There is some problem in getting the data");
	}
}
@GetMapping(value="/students/{id}")
public StudentDto getById(@PathVariable("id") int id) {
return studentService.getStudentById(id);
}

@PutMapping(value="/students/{id}")
public StudentDto update(@PathVariable("id") Integer id ,@RequestBody StudentRequest studentRequest) {
	logger.info("Request has studentupdate");
	
	studentRequest.setId(id);
	StudentDto updatedStudent=studentService.update(id, studentRequest);
	return updatedStudent;
}
@DeleteMapping(value="/students/{id}")
public String delete(@PathVariable Integer id) {
	return studentService.delete(id);
}



}
