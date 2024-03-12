package com.studentcrud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentcrud.dto.BankCDto;
import com.studentcrud.dto.BankCRequest;
import com.studentcrud.service.BankCService;

@RestController
public class BankCController {

	@Autowired
	private BankCService bankCService;
	
	Logger logger=LoggerFactory.getLogger(BankCController.class);
	
	@PostMapping(value="/bank")
	public ResponseEntity<?>createBank(@RequestBody BankCRequest request){
		BankCDto bank=bankCService.createBank(request);
		
		if(bank!=null) {
			logger.info("create bank");
			return ResponseEntity.ok(bankCService.createBank(request));
			
		}else {
			logger.error("create bank error");
			return ResponseEntity.badRequest().body("there is some error in create bank");
		}
	}
	@GetMapping(value="/bank")
	public List<BankCDto>getAllBank(){
		return bankCService.getAllBank();
		}
	
	@GetMapping(value="/bank/{id}")
	public BankCDto getBankById(@PathVariable("id") int id) {
		return bankCService.getById(id);
	}
	
	@PutMapping(value="/bank/{id}")
	public BankCDto update(@PathVariable("id")Integer id,@RequestBody BankCRequest request) {
		return bankCService.update(id,request);
	}
	
	@DeleteMapping(value="/bank/{id}")
	public String delete(@PathVariable Integer id) {
		return bankCService.delete(id);
	}
}
