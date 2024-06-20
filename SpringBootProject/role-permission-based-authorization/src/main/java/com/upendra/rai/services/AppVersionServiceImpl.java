package com.upendra.rai.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upendra.rai.dtos.AppVersionRequestDto;
import com.upendra.rai.dtos.AppVersionResponseDto;
import com.upendra.rai.entities.AppVersion;
import com.upendra.rai.repositories.AppVersionRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class AppVersionServiceImpl implements AppVersionService {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private AppVersionRepository appVersionRepository;

@Override
public AppVersionResponseDto createAppVersion(AppVersionRequestDto appVersionRequestDto) {
	
	AppVersion appVersion=modelMapper.map(appVersionRequestDto, AppVersion.class);
	AppVersion savedAppVersion=appVersionRepository.save(appVersion);
	AppVersionResponseDto appVersionResponseDto=modelMapper.map(savedAppVersion, AppVersionResponseDto.class);
	return appVersionResponseDto;

}

@Override
public AppVersionResponseDto getAppVersionById(Long appVersionId) {
	AppVersion appVersion=appVersionRepository.findById(appVersionId).get();
	return modelMapper.map(appVersion, AppVersionResponseDto.class);
}

@Override
public List<AppVersionResponseDto> getAllAppVersion() {
	List<AppVersion>appVersions=appVersionRepository.findAll();
	return appVersions.stream().map(appVersion->{
		AppVersionResponseDto appVersionResponseDto=new AppVersionResponseDto();
		appVersionResponseDto.setDescription(appVersion.getDescription());
		appVersionResponseDto.setId(appVersion.getId());
		appVersionResponseDto.setIsActive(appVersion.getIsActive());
		appVersionResponseDto.setVersion(appVersion.getVersion());
		appVersionResponseDto.setUuid(appVersion.getUuid());
		return appVersionResponseDto;
	}).collect(Collectors.toList());
}

@Override
public AppVersionResponseDto updateAppVersion(Long appVersionId, AppVersionRequestDto appVersionRequestDto) {
	AppVersion appVersion=appVersionRepository.findById(appVersionId).get();
	appVersion.setDescription(appVersionRequestDto.getDescription());
	appVersion.setVersion(appVersionRequestDto.getVersion());
	appVersion.setIsActive(appVersionRequestDto.getIsActive());
	appVersionRepository.save(appVersion);
	
	AppVersionResponseDto appVersionResponseDto=new AppVersionResponseDto();
	appVersionResponseDto.setDescription(appVersion.getDescription());
	appVersionResponseDto.setId(appVersion.getId());
	appVersionResponseDto.setIsActive(appVersion.getIsActive());
	appVersionResponseDto.setVersion(appVersion.getVersion());
	appVersionResponseDto.setUuid(appVersion.getUuid());

	return appVersionResponseDto;
}

@Override
public void activateAppVersion(Long appVersionId) {
	AppVersion appVersion=appVersionRepository.findById(appVersionId).get();
	appVersion.setIsActive(true);
}

@Override
public void deactivateAppVersion(Long appVersionId) {
	AppVersion appVersion=appVersionRepository.findById(appVersionId).get();
	appVersion.setIsActive(false);
	
}

}
