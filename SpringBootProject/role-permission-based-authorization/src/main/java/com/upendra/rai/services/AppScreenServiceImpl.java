package com.upendra.rai.services;

import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.querydsl.core.types.Predicate;
import com.upendra.rai.dtos.AppScreenRequestDto;
import com.upendra.rai.dtos.AppScreenResponseDto;
import com.upendra.rai.dtos.ClientResponseDto;
import com.upendra.rai.entities.AppScreen;
import com.upendra.rai.entities.Client;
import com.upendra.rai.exceptions.ResourceNotFoundException;
import com.upendra.rai.exceptions.ServiceException;
import com.upendra.rai.repositories.AppScreenRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppScreenServiceImpl implements AppScreenService {

  @Autowired
  private AppScreenRepository appScreenRepository;
  
  @Autowired
  private ModelMapper modelMapper;

  @Override
  @Transactional
  public AppScreenResponseDto createAppScreen(AppScreenRequestDto appScreenRequestDto) {
    
	  AppScreen appScreen=modelMapper.map(appScreenRequestDto, AppScreen.class);
	  AppScreen savedAppScreen=appScreenRepository.save(appScreen);
	  AppScreenResponseDto appScreenResponseDto=modelMapper.map(savedAppScreen, AppScreenResponseDto.class);
	  return appScreenResponseDto;
  }

  @Override
  @Transactional
  public AppScreenResponseDto updateAppScreen(Long appScreenId, AppScreenRequestDto appScreenRequestDto){
    log.info(">> updateAppScreen({}, {})", appScreenId, appScreenRequestDto);
    AppScreen appScreen = appScreenRepository.findById(appScreenId).get();
    appScreen.setAppScreenName(appScreenRequestDto.getAppScreenName());
    appScreenRepository.save(appScreen);
    
    AppScreenResponseDto appScreenResponseDto=new AppScreenResponseDto();
    appScreenResponseDto.setAppScreenName(appScreen.getAppScreenName());
    appScreenResponseDto.setIsActive(appScreen.getIsActive());
    return appScreenResponseDto;
  }
//@Override
//@Transactional
//  public AppScreenResponseDto updateAppScreen(Long appScreenId, AppScreenRequestDto appScreenRequestDto) {
//      // Fetch the existing entity
//      AppScreen existingAppScreen = appScreenRepository.findById(appScreenId)
//              .orElseThrow(() -> new ResourceNotFoundException("AppScreen not found with id " + appScreenId));
//
//      // Map the properties from the DTO to the existing entity
//      modelMapper.map(appScreenRequestDto, existingAppScreen);
//
//      // Save the updated entity
//      AppScreen updatedAppScreen = appScreenRepository.save(existingAppScreen);
//
//      // Convert the updated entity to a response DTO
//      return modelMapper.map(updatedAppScreen, AppScreenResponseDto.class);
//  }
  @Override
  public void deleteAppScreen(Long appScreenId) {
  AppScreen appScreen=appScreenRepository.findById(appScreenId).get();
  appScreenRepository.deleteById(appScreenId);
  }

  @Override
  @Transactional
  public void activateAppScreen(Long appScreenId) {
    log.info(">> activateAppScreen({})", appScreenId);
    AppScreen appScreen = appScreenRepository.findById(appScreenId).get();
    appScreen.setIsActive(true);
  }

  @Override
  @Transactional
  public void deactivateAppScreen(Long appScreenId) {
    log.info(">> deactivateAppScreen({})", appScreenId);
    AppScreen appScreen = appScreenRepository.findById(appScreenId).get();
    appScreen.setIsActive(false);
  }

 
  @Override
  @Transactional(readOnly = true)
  public AppScreenResponseDto getAppScreenById(Long appScreenId) {
	  AppScreen appScreen=appScreenRepository.findById(appScreenId).get();
	  return modelMapper.map(appScreen, AppScreenResponseDto.class);
  }



}
