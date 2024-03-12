package com.studentcrud.service;

import java.util.List;

import com.studentcrud.dto.BankCDto;
import com.studentcrud.dto.BankCRequest;

public interface BankCService {

	public BankCDto createBank(BankCRequest request);
	public List<BankCDto> getAllBank();
	public BankCDto getById(int id);
	public BankCDto update(Integer id, BankCRequest request);
	public String delete(Integer id);
	
}
