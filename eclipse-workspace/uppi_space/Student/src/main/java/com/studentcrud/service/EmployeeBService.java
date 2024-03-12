package com.studentcrud.service;

import java.util.List;

import com.studentcrud.dto.EmployeeBDto;
import com.studentcrud.dto.EmployeeBRequest;

public interface EmployeeBService {

	public EmployeeBDto createEmployee (EmployeeBRequest request);
	public List<EmployeeBDto>getAllEmployee();
	public EmployeeBDto getById(Integer id);
	public EmployeeBDto update(Integer id,EmployeeBRequest request);
	public String delete(Integer id);
}
