package com.studentcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentcrud.dto.LibraryDto;
import com.studentcrud.dto.LibraryRequest;
import com.studentcrud.service.LibraryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LibraryController {

	@Autowired
	private LibraryService libraryService;
	
	
	@PostMapping(value="/library")
	public ResponseEntity<?>createLibrary(@RequestBody LibraryRequest request){
		LibraryDto dto=libraryService.createLibrary(request);
		if(dto!=null) {
			return ResponseEntity.ok(libraryService.createLibrary(request));
		}else {
			return ResponseEntity.badRequest().body("There is some problem in create library");
		}
	}
	
	@GetMapping(value="/library")
	public List<LibraryDto>getAll(){
		return libraryService.getAll();
	}
	
}
