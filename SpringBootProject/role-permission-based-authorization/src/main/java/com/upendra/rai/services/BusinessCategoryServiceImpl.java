package com.upendra.rai.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upendra.rai.dtos.AppSidebarResponseDto;
import com.upendra.rai.dtos.BusinessCategoryRequestDto;
import com.upendra.rai.dtos.BusinessCategoryResponseDto;
import com.upendra.rai.entities.AppIcon;
import com.upendra.rai.entities.AppSidebar;
import com.upendra.rai.entities.BusinessCategory;
import com.upendra.rai.entities.Client;
import com.upendra.rai.repositories.BusinessCategoryRepository;
import com.upendra.rai.repositories.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BusinessCategoryServiceImpl implements BusinessCategoryService {

  @Autowired
  private BusinessCategoryRepository businessCategoryRepository;


  @Autowired
  private ModelMapper modelMapper;
  
  @Autowired
  private ClientRepository clientRepository;

//
//  @Value("${application.cdn.env}")
//  private String cdnEnv;
//
//  @Value("${application.cdn.media-path}")
//  private String mediaPath;
//
//  @Value("${application.client.business-category-icon.path}")
//  protected String iconImagePath;
//
//  @Value("${application.business-category-icon.image.path}")
//  protected String businessCategoryIconImagePath;

@Override
@Transactional
public BusinessCategoryResponseDto createBusinessCategory(Long clientId,BusinessCategoryRequestDto businessCategoryRequestDto) {
	Client client=clientRepository.findById(clientId).get();
	BusinessCategory businessCategory=modelMapper.map(businessCategoryRequestDto, BusinessCategory.class);
	businessCategory.setClient(client);
	businessCategory.setSortOrder(4);
	businessCategory.setIsActive(true);
	BusinessCategory savedBusinessCategory=businessCategoryRepository.save(businessCategory);
	BusinessCategoryResponseDto businessCategoryResponseDto=modelMapper.map(savedBusinessCategory, BusinessCategoryResponseDto.class);
	return businessCategoryResponseDto;
}

@Override
public List<BusinessCategoryResponseDto> getAllBusinessCategory() {
	
	List<BusinessCategory>BusinessCategories=businessCategoryRepository.findallwithBusiness();
	return BusinessCategories.stream().map(bs->{
		BusinessCategoryResponseDto businessCategoryResponseDto=new BusinessCategoryResponseDto();
		businessCategoryResponseDto.setClient(bs.getClient());
		businessCategoryResponseDto.setBusinessCategoryId(bs.getId());
		businessCategoryResponseDto.setCategory(bs.getCategory());
		businessCategoryResponseDto.setIconImagePath(bs.getIconImagePath());
		businessCategoryResponseDto.setIconImagePath(bs.getIconImagePath());
		businessCategoryResponseDto.setIsActive(bs.getIsActive());
		businessCategoryResponseDto.setSortOrder(bs.getSortOrder());
		return businessCategoryResponseDto;
	}).collect(Collectors.toList());
}


//+++it is not working properly ++++++++++++

@Override
public BusinessCategoryResponseDto getByBusineesbyId(Long clientId, Long businessId) {
	Client client=clientRepository.findById(businessId).get();
	BusinessCategory businessCategory=businessCategoryRepository.findByIdWithClient(businessId, client).get();
	return modelMapper.map(businessCategory, BusinessCategoryResponseDto.class);
}

@Override
public List<BusinessCategoryResponseDto> getBusinessIdCategory(Long clientId) {
	Client client=clientRepository.findById(clientId).get();
	List<BusinessCategory> businessCategories=businessCategoryRepository.findAllAndClient(client);
	return businessCategories.stream().map(bs->modelMapper.map(bs, BusinessCategoryResponseDto.class)).collect(Collectors.toList());
}

@Override
public BusinessCategoryResponseDto getclientBusinessCategoryId(Long businessId,Long clientId) {
	Client client=clientRepository.findById(clientId).get();
	BusinessCategory businessCategory=businessCategoryRepository.findById(businessId).get();
	BusinessCategoryResponseDto businessCategoryResponseDto=new BusinessCategoryResponseDto();
	businessCategoryResponseDto.setCategory(businessCategory.getCategory());
	businessCategoryResponseDto.setBusinessCategoryId(businessCategory.getId());
	businessCategoryResponseDto.setClient(client);
	businessCategoryResponseDto.setIconImagePath(businessCategory.getIconImagePath());
	businessCategoryResponseDto.setIsActive(businessCategory.getIsActive());
	businessCategoryResponseDto.setSortOrder(businessCategory.getSortOrder());
	return businessCategoryResponseDto;
}

}
