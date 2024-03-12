package com.studentcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentcrud.dto.AddressDto;
import com.studentcrud.dto.AddressRequest;
import com.studentcrud.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService service;
	
	@PostMapping(value="/address")
	public ResponseEntity<?>createAddress(@RequestBody AddressRequest request){
		AddressDto dto=service.createAddress(request);
		if(dto!=null) {
			return ResponseEntity.ok(service.createAddress(request));
		}else {
			return ResponseEntity.badRequest().body("There is some problem in create address");
		}
	}
	
	@GetMapping(value="/address")
	public List<AddressDto> getAll() {
		return service.getAll();
		
	}

}
