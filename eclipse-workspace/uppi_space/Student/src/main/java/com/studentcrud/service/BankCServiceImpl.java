package com.studentcrud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentcrud.dto.BankCDto;
import com.studentcrud.dto.BankCRequest;
import com.studentcrud.entity.BankC;
import com.studentcrud.repo.BankCRepo;

@Service
public class BankCServiceImpl implements BankCService {

	Logger logger=LoggerFactory.getLogger(BankCServiceImpl.class);
	@Autowired
	private BankCRepo bankCRepo;
	@Override
	public BankCDto createBank(BankCRequest request) {//request to entity
		
		BankC entity=new BankC();
		entity.setCustomer_name(request.getCustomer_name());
		entity.setBank_account(request.getBank_account());
		entity.setGender(request.getGender());
		
		bankCRepo.save(entity);
		
		//entity to dto
		BankCDto dto=new BankCDto();
		dto.setId(entity.getId());
		dto.setCustomer_name(entity.getCustomer_name());
		dto.setBank_account(entity.getBank_account());
		dto.setGender(entity.getGender());
		return dto;
		
	}
	@Override
	public List<BankCDto> getAllBank() {
		List<BankC> bank=bankCRepo.findAll();
		return bank.stream().map(bk->{
			BankCDto dto=new BankCDto();
			dto.setId(bk.getId());
			dto.setBank_account(bk.getBank_account());
			dto.setCustomer_name(bk.getCustomer_name());
			dto.setGender(bk.getGender());
			return dto;
		}).collect(Collectors.toList());
	}
	@Override
	public BankCDto getById(int id) {
		BankC bank=bankCRepo.findById(id).get();
		BankCDto dto=new BankCDto();
		dto.setId(bank.getId());
		dto.setBank_account(bank.getBank_account());
		dto.setCustomer_name(bank.getCustomer_name());
		dto.setGender(bank.getGender());
		return dto;
	}
	@Override
	public BankCDto update(Integer id, BankCRequest request) {
		BankC entity=bankCRepo.findById(id).get();
		entity.setCustomer_name(request.getCustomer_name());
		entity.setBank_account(request.getBank_account());
		entity.setGender(request.getGender());
		bankCRepo.save(entity);
		
		BankCDto dto=new BankCDto();
		dto.setId(entity.getId());
		dto.setCustomer_name(entity.getCustomer_name());
		dto.setBank_account(entity.getBank_account());
		dto.setGender(entity.getGender());
		return dto;
	}
	
	@Override
	public String delete(Integer id) {
		BankC entity=bankCRepo.findById(id).get();
		bankCRepo.delete(entity);
		return "deleted";
	}

}
