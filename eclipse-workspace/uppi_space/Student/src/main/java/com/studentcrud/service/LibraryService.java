package com.studentcrud.service;

import java.util.List;

import com.studentcrud.dto.LibraryDto;
import com.studentcrud.dto.LibraryRequest;

public interface LibraryService {

	public LibraryDto createLibrary(LibraryRequest request);

	public List<LibraryDto> getAll();
}
