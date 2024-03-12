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

import com.studentcrud.dto.DepartmentDDTO;
import com.studentcrud.dto.DepartmentDRequest;
import com.studentcrud.dto.DepartmentRequest;
import com.studentcrud.entity.DepartmentD;
import com.studentcrud.repo.DepartmentDRepo;
import com.studentcrud.service.DepartmentDService;
import com.studentcrud.service.DepartmentDServiceImpl;

@RestController

public class DepartmentDController {

	Logger logger =LoggerFactory.getLogger(DepartmentDServiceImpl.class);
	
	@Autowired
	private DepartmentDService departmentDService;
	
	@Autowired 
	private DepartmentDRepo depRepo;
	@PostMapping(value="/department")
	public ResponseEntity<?> createDepartmentD(@RequestBody DepartmentDRequest request) {
		DepartmentDDTO entity= departmentDService.createDepartmentD(request);
		if(entity!=null) {
			logger.info("create department");
			return ResponseEntity.ok(departmentDService.createDepartmentD(request)); 
		}else {
			logger.info("there is something problem in create department");
			return ResponseEntity.badRequest().body("there is something problem in create department");
		}
	}
	
	@GetMapping(value="/department")
	public List<DepartmentDDTO>getDepartement(){
		logger.info("getAll department");
		return departmentDService.getAllDepartment();
		
	}
	
	@GetMapping(value="/department/{id}")
	public DepartmentDDTO getDepartmentById(@PathVariable("id")Integer id) {
		return departmentDService.getDepartById(id);
	}
	
	@PutMapping(value="/department/{id}")
	public DepartmentDDTO update(@PathVariable("id")Integer id,@RequestBody DepartmentDRequest request) {
		return departmentDService.update(id,request);
	}
	
	@DeleteMapping(value="/department/{id}")
	public String deletebyid(@PathVariable("id")Integer id) {
		return departmentDService.delete(id);
	}
	
	@PostMapping(value="/departmentem")
	public DepartmentD createDeparment(@RequestBody DepartmentRequest request) {
		return depRepo.save(request.getDepartment());
	}
		
	
}
