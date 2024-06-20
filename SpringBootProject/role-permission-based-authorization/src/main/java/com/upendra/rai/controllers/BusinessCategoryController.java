package com.upendra.rai.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upendra.rai.dtos.AppSidebarResponseDto;
import com.upendra.rai.dtos.BusinessCategoryRequestDto;
import com.upendra.rai.dtos.BusinessCategoryResponseDto;
import com.upendra.rai.entities.BusinessCategory;
import com.upendra.rai.services.BusinessCategoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/businessCategories")
public class BusinessCategoryController {

  @Autowired
  private BusinessCategoryService businessCategoryService;

@PostMapping("/{clientId}")
public BusinessCategoryResponseDto createBusinessCategory(@PathVariable("clientId") Long clientId,@RequestBody BusinessCategoryRequestDto businessCategoryRequestDto) {
  log.info(">> createBusinessCategory({}, {})", clientId, businessCategoryRequestDto);
  return businessCategoryService.createBusinessCategory(clientId, businessCategoryRequestDto);
}

  
@GetMapping("/getAllBusiness")
public List<BusinessCategoryResponseDto> getAllBusinessCategory() {
	return businessCategoryService.getAllBusinessCategory();
}

//+++it is not working properly++++++++++++
@GetMapping("/{businessId}")
public BusinessCategoryResponseDto getByBusineesbyId(@RequestHeader Long clientId,@PathVariable("businessId") Long businessId) {
return businessCategoryService.getByBusineesbyId(clientId, businessId);
}
@GetMapping
public List<BusinessCategoryResponseDto> getBusinessCategory(@RequestHeader("clientId")Long clientId) {
	return businessCategoryService.getBusinessIdCategory(clientId);
}
@GetMapping("/business/{businessId}")
public  BusinessCategoryResponseDto getclientBusinessCategoryId(@PathVariable("businessId")Long businessId,@RequestHeader("clientId")Long clientId) {
	return businessCategoryService.getclientBusinessCategoryId(businessId,clientId);
}

}
