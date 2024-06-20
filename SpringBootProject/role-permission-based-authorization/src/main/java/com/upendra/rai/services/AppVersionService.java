package com.upendra.rai.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.upendra.rai.dtos.AppVersionRequestDto;
import com.upendra.rai.dtos.AppVersionResponseDto;

public interface AppVersionService {


	AppVersionResponseDto createAppVersion(AppVersionRequestDto appVersionRequestDto);
	
	public List<AppVersionResponseDto> getAllAppVersion();

    public AppVersionResponseDto updateAppVersion(Long appVersionId,AppVersionRequestDto appVersionRequestDto);

//  void deleteAppVersion(String appVersionId);
//
  public void activateAppVersion(Long appVersionId);
  
  public void deactivateAppVersion(Long appVersionId);
//
//  void deactivateAppVersion(String appVersionId);
//
//  void makeAppVersionDefault(String appVersionId);

 public AppVersionResponseDto getAppVersionById(
	      @PathVariable Long appVersionId);
}
