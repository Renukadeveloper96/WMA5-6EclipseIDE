package com.upendra.rai.services;

import com.upendra.rai.dtos.AppScreenRequestDto;
import com.upendra.rai.dtos.AppScreenResponseDto;

public interface AppScreenService {


	public AppScreenResponseDto createAppScreen(AppScreenRequestDto appScreenRequestDto);

	public AppScreenResponseDto updateAppScreen(Long appScreenId, AppScreenRequestDto appScreenRequestDto);

	public void deleteAppScreen(Long appScreenId);

  void activateAppScreen(Long appScreenId);

  void deactivateAppScreen(Long appScreenId);

  public  AppScreenResponseDto getAppScreenById(Long appScreenId);

}
