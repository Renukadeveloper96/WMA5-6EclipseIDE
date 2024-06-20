package com.upendra.rai.services;

import java.util.List;

import com.upendra.rai.dtos.AppSidebarResponseDto;
import com.upendra.rai.dtos.BusinessCategoryRequestDto;
import com.upendra.rai.dtos.BusinessCategoryResponseDto;

public interface BusinessCategoryService {


	public BusinessCategoryResponseDto createBusinessCategory(Long clientId,BusinessCategoryRequestDto businessCategoryRequestDto);

	public List<BusinessCategoryResponseDto> getAllBusinessCategory();
	
	
	//+++it is not working properly++++++++++++
	public BusinessCategoryResponseDto getByBusineesbyId(Long clientId,Long businessId);
	
	
	public List<BusinessCategoryResponseDto> getBusinessIdCategory(Long clientId);

	public BusinessCategoryResponseDto getclientBusinessCategoryId(Long businessId,Long clientId);
	
	
//  void updateBusinessCategory(String businessCategoryId,
//      BusinessCategoryRequestDto businessCategoryRequestDto);
//
//  void deleteBusinessCategory(String clientId, String businessCategoryId);
//
//  void activateBusinessCategory(String clientId, String businessCategoryId);
//
//  void deactivateBusinessCategory(String clientId, String businessCategoryId);
//
//  BusinessCategoryResponseDto getBusinessCategoryById(String clientId, String businessCategoryId,
//      Optional<String> timezone);
//
//  void sort(String clientId, SortRequestDto sortDto);
}
