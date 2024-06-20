package com.upendra.rai.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upendra.rai.dtos.AppSidebarResponseDto;
import com.upendra.rai.dtos.AppTypeRequestDto;
import com.upendra.rai.dtos.AppTypeResponseDto;
import com.upendra.rai.entities.AppIcon;
import com.upendra.rai.entities.AppSidebar;
import com.upendra.rai.entities.AppType;
import com.upendra.rai.entities.AppVersion;
import com.upendra.rai.repositories.AppTypeRepository;
import com.upendra.rai.repositories.AppVersionRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppTypeServiceImpl implements AppTypeService {

  @Autowired
  private AppTypeRepository appTypeRepository;
  
  @Autowired
  private AppVersionRepository appVersionRepository;
  
  @Autowired
  private ModelMapper modelMapper;

@Override
public AppTypeResponseDto createAppType(Long id, AppTypeRequestDto appTypeRequestDto) {
	AppVersion appVersion=appVersionRepository.findById(id).get();
	AppType appType=modelMapper.map(appTypeRequestDto, AppType.class);
	appType.setAppVersions(appVersion);
	AppType savedAppType=appTypeRepository.save(appType);
	AppTypeResponseDto appTypeResponseDto=modelMapper.map(savedAppType, AppTypeResponseDto.class);
	return appTypeResponseDto;
	
}

@Override
public List<AppTypeResponseDto> getAllAppType() {
	List<AppType>appTypes=appTypeRepository.findAllWithAppType();
	
	return appTypes.stream().map(appType->{
	
		AppTypeResponseDto appTypeResponseDto=new AppTypeResponseDto();
		appTypeResponseDto.setAppVersion(appType.getAppVersions()); // Use the appIcon from the entity
		appTypeResponseDto.setAppTypeId(appType.getId());
	  	  appTypeResponseDto.setAppTypeLabel(appType.getAppTypeLabel());
	  	  appTypeResponseDto.setAppTypeCode(appType.getAppTypeCode());
	  	  appTypeResponseDto.setIsActive(appType.getIsActive());
	  	  appTypeResponseDto.setDescription(appType.getDescription());
        return appTypeResponseDto;
		
	}).collect(Collectors.toList());
	
}

@Override
public AppTypeResponseDto getAppTypeById(Long appTypeId, Long appVersionId) {
	AppVersion appVersion=appVersionRepository.findById(appVersionId).get();
	AppType appType=appTypeRepository.findbyIdAndAppType(appTypeId, appVersion).get();
	return modelMapper.map(appType, AppTypeResponseDto.class);
}

@Override
public AppTypeResponseDto updateAppType(Long appTypeId, AppTypeRequestDto appTypeRequestDto) {
	
	AppType appType=appTypeRepository.findById(appTypeId).get();
	appType.setAppTypeCode(appTypeRequestDto.getAppTypeCode());
	appType.setAppTypeLabel(appTypeRequestDto.getAppTypeLabel());
	appType.setDescription(appTypeRequestDto.getDescription());
	appTypeRepository.save(appType);
	 
	AppTypeResponseDto appTypeResponseDto=new AppTypeResponseDto();
	appTypeResponseDto.setAppTypeCode(appType.getAppTypeCode());
	appTypeResponseDto.setAppTypeLabel(appType.getAppTypeLabel());
	appTypeResponseDto.setDescription(appType.getDescription());
	return appTypeResponseDto;
}

//  @Override
//  @Transactional
//public AppTypeResponseDto createAppType(Long id,AppTypeRequestDto appTypeRequestDto) {
//AppVersion appVersion=appVersionRepository.findById(id).get();
//AppType appType=modelMapper.map(appTypeRequestDto, AppType.class);
//appType.setAppVersions(appVersion);
//appType.setIsActive(true);
//AppType savedAppType=appTypeRepository.save(appType);
//AppTypeResponseDto appTypeResponseDto=modelMapper.map(savedAppType, AppTypeResponseDto.class);
//return appTypeResponseDto;
//
//}
//
//  @Override
//  public List<AppTypeResponseDto> getAllAppType(){
//
//  	// Fetch all AppSidebar entities with their associated AppIcon
//      List<AppType> appTypes = appTypeRepository.findAllWithAppType();
//
//      // Convert AppSidebar entities to AppSidebarResponseDto
//      return appTypes.stream()
//              .map(appType -> {
//            	  AppTypeResponseDto appTypeResponseDto = new AppTypeResponseDto();
//            	  appTypeResponseDto.setAppVersion(appType.getAppVersions()); // Use the appIcon from the entity
//            	  appTypeResponseDto.setAppTypeLabel(appType.getAppTypeLabel());
//            	  appTypeResponseDto.setAppTypeCode(appType.getAppTypeCode());
//            	  appTypeResponseDto.setIsActive(appType.getIsActive());
//            	  appTypeResponseDto.setDescription(appType.getDescription());
//                  return appTypeResponseDto;
//              })
//              .collect(Collectors.toList());
//  		}
//
//  @Override
//  public AppTypeResponseDto getAppTypeById(Long appVersionId, Long appTypeId) {
//  	AppVersion appVersion=appVersionRepository.findById(appVersionId).get();
//  	AppType appType=appTypeRepository.findByIdAndAppType(appTypeId, appVersion).get();
//  	return modelMapper.map(appType, AppTypeResponseDto.class);
//  }
}
	

