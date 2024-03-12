package com.studentcrud.service;

import java.util.List;

import com.studentcrud.dto.AddressDto;
import com.studentcrud.dto.AddressRequest;

public interface AddressService {

	public AddressDto createAddress(AddressRequest request);

	public List<AddressDto> getAll();

}
