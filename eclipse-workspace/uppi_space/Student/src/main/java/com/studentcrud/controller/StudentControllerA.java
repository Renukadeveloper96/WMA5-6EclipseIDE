package com.studentcrud.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.studentcrud.dto.StudentDtoA;
import com.studentcrud.dto.StudentRequestA;
import com.studentcrud.entity.Studentt;
import com.studentcrud.repo.StudenttRepository;
import com.studentcrud.service.StudentServiceA;


@RestController
public class StudentControllerA {

	@Autowired
	private StudentServiceA studentServiceA;
	
	Logger logger = LoggerFactory.getLogger(StudentControllerA.class);
	
	@PostMapping("/studentsa")
	public ResponseEntity<?>createStudent(@RequestBody StudentRequestA studentRequestA){
		StudentDtoA studentDtoA=studentServiceA.createStudenta(studentRequestA);
		if(studentDtoA!=null) {
			logger.info("result of create StudentA is excecuted");
			return ResponseEntity.ok(studentServiceA.createStudenta(studentRequestA));
		}else {
			logger.error("create studentsa error");
			return ResponseEntity.badRequest().body("There is some problem in studenta post data");
		}
	}
	
	@GetMapping(value="/studentsa")
	public List<StudentDtoA>getAllStudenta(){
		return studentServiceA.getAll();
	}
	
	@GetMapping(value="/studentsa/{id}")
	public StudentDtoA getById(@PathVariable("id") int id) {
		return studentServiceA.getById(id);
	}
	
	@PutMapping(value="/studentsa/{id}")
	public StudentDtoA update(@PathVariable("id") Integer id,@RequestBody  StudentRequestA studentRequestA) {
		return studentServiceA.update(id, studentRequestA);
	}
	@DeleteMapping(value="/studentsa/{id}")
	public String delete(@PathVariable("id")Integer id) {
		return studentServiceA.delete(id);
	}
	
	@Autowired
	StudenttRepository studenttRepo;
	
	@PostMapping(value="/studentt")
	Studentt createStudentt(@RequestBody Studentt studentt) {
		return studenttRepo.save(studentt);
	}
}
