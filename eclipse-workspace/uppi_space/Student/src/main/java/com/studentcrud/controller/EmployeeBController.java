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

import com.studentcrud.dto.EmployeeBDto;
import com.studentcrud.dto.EmployeeBRequest;
import com.studentcrud.service.EmployeeBService;

@RestController
public class EmployeeBController {

	Logger logger=LoggerFactory.getLogger(EmployeeBController.class);
	@Autowired
	private EmployeeBService employeeBService;
	
	@PostMapping(value="/employee")
	public ResponseEntity<?>createEmployee(@RequestBody EmployeeBRequest employeeBRequest){
		
		EmployeeBDto employeeBDto=employeeBService.createEmployee(employeeBRequest);
		
		if(employeeBDto!=null) {
			logger.info("create employee ");
			return ResponseEntity.ok(employeeBService.createEmployee(employeeBRequest));
		}else {
			return ResponseEntity.badRequest().body("there is error in create  employee");
		}
	}
	
	@GetMapping(value="/employee")
		public List<EmployeeBDto>getAllEmployee(){
			return employeeBService.getAllEmployee();
		}
	
	@GetMapping(value="/employee/{id}")
	public EmployeeBDto getById(@PathVariable int id) {
		return employeeBService.getById(id);
	}
	
	@PutMapping(value="/employee/{id}")
	public EmployeeBDto update(@PathVariable("id")Integer id,@RequestBody EmployeeBRequest request) {
		return employeeBService.update(id, request);
	}
	
	@DeleteMapping(value="/employee/{id}")
	public String delete(@PathVariable("id") Integer id) {
		return employeeBService.delete(id);
	}
	
}