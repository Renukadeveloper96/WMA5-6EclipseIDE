package com.studentcrud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentcrud.dto.EmployeeBDto;
import com.studentcrud.dto.EmployeeBRequest;
import com.studentcrud.entity.EmployeeB;
import com.studentcrud.repo.EmployeeBRepo;

@Service
public class EmployeeBServiceImpl implements EmployeeBService{

	Logger logger=LoggerFactory.getLogger(EmployeeBServiceImpl.class);
	
	
	@Autowired
	private EmployeeBRepo employeeBRepo;
	
	@Override
	public EmployeeBDto createEmployee(EmployeeBRequest request) {//request to entity;
		
		EmployeeB entity=new EmployeeB();
		entity.setEmp_name(request.getEmp_name());
		entity.setEmp_salary(request.getEmp_salary());
		employeeBRepo.save(entity);
		
		//entity to dto
		EmployeeBDto dto =new EmployeeBDto();
		dto.setId(entity.getId());
		dto.setEmp_name(entity.getEmp_name());
		dto.setEmp_salary(entity.getEmp_salary());
		return dto;
	
	}

	@Override
	public List<EmployeeBDto> getAllEmployee() {
		List<EmployeeB>employeeb=employeeBRepo.findAll();
		return employeeb.stream().map(eb->{
			EmployeeBDto dto=new EmployeeBDto();
			dto.setId(eb.getId());
			dto.setEmp_name(eb.getEmp_name());
			dto.setEmp_salary(eb.getEmp_salary());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public EmployeeBDto getById(Integer id) {
		EmployeeB eb=employeeBRepo.getById(id);
		EmployeeBDto dto=new EmployeeBDto();
		dto.setId(eb.getId());
		dto.setEmp_name(eb.getEmp_name());
		dto.setEmp_salary(eb.getEmp_salary());
		return dto;
	}

	@Override
	public EmployeeBDto update(Integer id, EmployeeBRequest request) {
		EmployeeB entity=employeeBRepo.getById(id);
		entity.setEmp_name(request.getEmp_name());
		entity.setEmp_salary(request.getEmp_salary());
		employeeBRepo.save(entity);
		
		EmployeeBDto dto=new EmployeeBDto();
		dto.setEmp_name(entity.getEmp_name());
		dto.setEmp_salary(entity.getEmp_salary());
		dto.setId(entity.getId());
		return dto;
	}

	@Override
	public String delete(Integer id) {
		EmployeeB emp=employeeBRepo.findById(id).get();
		employeeBRepo.delete(emp);
		return "deleted";
	}

}
