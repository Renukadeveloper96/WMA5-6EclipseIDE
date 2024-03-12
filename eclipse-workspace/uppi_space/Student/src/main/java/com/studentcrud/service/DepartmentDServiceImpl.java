package com.studentcrud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentcrud.dto.DepartmentDDTO;
import com.studentcrud.dto.DepartmentDRequest;
import com.studentcrud.entity.DepartmentD;
import com.studentcrud.repo.DepartmentDRepo;

@Service
public class DepartmentDServiceImpl implements DepartmentDService {

	@Autowired
	private DepartmentDRepo departmentDRepo;
	
	Logger logger =LoggerFactory.getLogger(DepartmentDServiceImpl.class);
	
	@Override
	public DepartmentDDTO createDepartmentD(DepartmentDRequest request) {//request to entity;
		DepartmentD entity=new DepartmentD();
		entity.setDepartmentName(request.getDepartmentName());
		entity.setDepartmentCode(request.getDepartmentCode());
		entity.setDepartmentAddress(request.getDepartmentAddress());
		departmentDRepo.save(entity);
		
		//entity to dto
		
		DepartmentDDTO dto=new DepartmentDDTO();
		dto.setId(entity.getId());
		dto.setDepartmentName(entity.getDepartmentName());
		dto.setDepartmentCode(entity.getDepartmentCode());
		dto.setDepartmentAddress(entity.getDepartmentAddress());
		return dto;
	
	}

	@Override
	public List<DepartmentDDTO> getAllDepartment() {
		 List<DepartmentD>entity=departmentDRepo.findAll();
		 return entity.stream().map(dt->{
			DepartmentDDTO dto=new DepartmentDDTO();
			dto.setId(dt.getId());
			dto.setDepartmentAddress(dt.getDepartmentAddress());
			dto.setDepartmentCode(dt.getDepartmentCode());
			dto.setDepartmentName(dt.getDepartmentName());
			return dto;
		}).collect(Collectors.toList());
		 
	}

	@Override
	public DepartmentDDTO getDepartById(Integer id) {
		DepartmentD entity=departmentDRepo.findById(id).get();
		DepartmentDDTO dto=new DepartmentDDTO();
		dto.setDepartmentName(entity.getDepartmentName());
		dto.setDepartmentCode(entity.getDepartmentCode());
		dto.setDepartmentAddress(entity.getDepartmentAddress());
		dto.setId(entity.getId());
		return dto;
		
	}

	@Override
	public DepartmentDDTO update(Integer id, DepartmentDRequest request) {
		DepartmentD entity=departmentDRepo.findById(id).get();
		entity.setDepartmentName(request.getDepartmentName());
		entity.setDepartmentCode(request.getDepartmentCode());
		entity.setDepartmentAddress(request.getDepartmentAddress());
		departmentDRepo.save(entity);
		
		DepartmentDDTO dto=new DepartmentDDTO();
		dto.setId(entity.getId());
		dto.setDepartmentName(entity.getDepartmentName());
		dto.setDepartmentCode(entity.getDepartmentCode());
		dto.setDepartmentAddress(entity.getDepartmentAddress());
		return dto;
	}

	@Override
	public String delete(Integer id) {
		DepartmentD entity=departmentDRepo.findById(id).get();
		departmentDRepo.delete(entity);
		return "deleted";
	}

}
