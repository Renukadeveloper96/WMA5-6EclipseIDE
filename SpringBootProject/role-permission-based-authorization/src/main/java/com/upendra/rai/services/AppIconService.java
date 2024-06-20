package com.upendra.rai.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.upendra.rai.dtos.AppIconRequestDto;
import com.upendra.rai.dtos.AppIconResponseDto;
import com.upendra.rai.dtos.ClientResponseDto;

@Service
public interface AppIconService {


	AppIconResponseDto createAppIcon(AppIconRequestDto appIconRequestDto);
	  public List<AppIconResponseDto> getAppIcon();

	  public AppIconResponseDto getAppIconById(Long id);
	  
	  public AppIconResponseDto updateAppIconById(Long id,AppIconRequestDto appIconRequestDto);
	  
	  public String deleteAppIconById(Long id);
}
