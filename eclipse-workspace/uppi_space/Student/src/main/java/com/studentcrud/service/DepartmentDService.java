package com.studentcrud.service;

import java.util.List;

import com.studentcrud.dto.DepartmentDDTO;
import com.studentcrud.dto.DepartmentDRequest;

public interface DepartmentDService {

	public DepartmentDDTO createDepartmentD(DepartmentDRequest request);

	public List<DepartmentDDTO> getAllDepartment();

	public DepartmentDDTO getDepartById(Integer id);

	public DepartmentDDTO update(Integer id, DepartmentDRequest request);

	public String delete(Integer id);
}
