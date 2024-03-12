package com.studentcrud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentcrud.dto.AddressDto;
import com.studentcrud.dto.AddressRequest;
import com.studentcrud.entity.Address;
import com.studentcrud.repo.AddressRepo;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepo repo;
	@Override
	public AddressDto createAddress(AddressRequest request) {
		Address entity=new Address();
		entity.setLocation(request.getLocation());
		repo.save(entity);
		
		AddressDto dto=new AddressDto();
		dto.setId(entity.getId());
		dto.setLocation(entity.getLocation());
		return dto;
	}
	@Override
	public List<AddressDto> getAll() {
		List<Address>address=repo.findAll();
		return address.stream().map(entity->{
			AddressDto dto=new AddressDto();
			dto.setId(entity.getId());
			dto.setLocation(entity.getLocation());
			return dto;
		}).collect(Collectors.toList());		
		
	}

}
