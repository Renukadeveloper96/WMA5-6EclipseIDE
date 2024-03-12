package com.studentcrud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentcrud.dto.LibraryDto;
import com.studentcrud.dto.LibraryRequest;
import com.studentcrud.entity.Library;
import com.studentcrud.repo.LibraryRepo;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryRepo repo;
	
	@Override
	public LibraryDto createLibrary(LibraryRequest request) {
		Library entity=new Library();
		entity.setAddress(request.getAddress());
		entity.setName(request.getName());
		repo.save(entity);
		
		//entity to dto
		LibraryDto dto=new LibraryDto();
		dto.setAddress(entity.getAddress());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public List<LibraryDto> getAll() {
		List<Library>entity=repo.findAll();
		return entity.stream().map(lr->{
			LibraryDto dto=new LibraryDto();
			dto.setAddress(lr.getAddress());
			dto.setId(lr.getId());
			dto.setName(lr.getName());
			return dto;
		}).collect(Collectors.toList());
	}

}
