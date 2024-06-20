package com.upendra.rai.services;

import java.util.List;

import com.upendra.rai.dtos.AppTypeRequestDto;
import com.upendra.rai.dtos.AppTypeResponseDto;

public interface AppTypeService {



	public AppTypeResponseDto createAppType(Long id,AppTypeRequestDto appTypeRequestDto);
	
	public List<AppTypeResponseDto> getAllAppType();
//	
	public AppTypeResponseDto getAppTypeById(Long appTypeId, Long appVersionId);

	
  public AppTypeResponseDto updateAppType(Long appTypeId, AppTypeRequestDto appTypeRequestDto);
//
//  void deleteAppType(String appTypeId);
//
//  void activateAppType(String appTypeId);
//
//  void deactivateAppType(String appTypeId);
//

}
