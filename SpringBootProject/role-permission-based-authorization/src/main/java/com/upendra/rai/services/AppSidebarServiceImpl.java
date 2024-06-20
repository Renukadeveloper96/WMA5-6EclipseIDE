package com.upendra.rai.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upendra.rai.dtos.AppScreenRequestDto;
import com.upendra.rai.dtos.AppScreenResponseDto;
import com.upendra.rai.dtos.AppSidebarRequestDto;
import com.upendra.rai.dtos.AppSidebarResponseDto;
import com.upendra.rai.entities.AppIcon;
import com.upendra.rai.entities.AppScreen;
import com.upendra.rai.entities.AppSidebar;
import com.upendra.rai.repositories.AppIconRepository;
import com.upendra.rai.repositories.AppSidebarRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppSidebarServiceImpl implements AppSidebarService {

  @Autowired
  private AppSidebarRepository appSidebarRepository;

  @Autowired
  private ModelMapper modelMapper;
  
  @Autowired
  private AppIconRepository appIconRepository;

  
  @Override
  @Transactional
  public	AppSidebarResponseDto createAppSidebar(Long id,AppSidebarRequestDto appSidebarRequestDto) {
	  AppIcon appIcon=appIconRepository.findById(id).get();
	  AppSidebar appSidebar=modelMapper.map(appSidebarRequestDto, AppSidebar.class);
	  appSidebar.setAppIcon(appIcon);
	  appSidebar.setSortOrder(2);
	  appSidebar.setIsActive(true);
	  AppSidebar savedAppSidebar=appSidebarRepository.save(appSidebar);
	  AppSidebarResponseDto appSidebarResponseDto=modelMapper.map(savedAppSidebar, AppSidebarResponseDto.class);
	  return appSidebarResponseDto;  
  }

@Override
public AppSidebarResponseDto getAppSidebarById(Long appIconId, Long appSidebarId) {
	AppIcon appIcon=appIconRepository.findById(appIconId).get();
	AppSidebar appSidebar=appSidebarRepository.findByIdAndAppIcon(appSidebarId, appIcon).get();
	return modelMapper.map(appSidebar, AppSidebarResponseDto.class);
}

@Override
public List<AppSidebarResponseDto> getAllAnnouncementTopic(Long appIconId) {
	AppIcon appIcon=appIconRepository.findById(appIconId).get();
	List<AppSidebar>appSidebar=appSidebarRepository.findAllAndAppIcon(appIcon);
	return appSidebar.stream().map(as->modelMapper.map(as,AppSidebarResponseDto.class )).collect(Collectors.toList());
}

@Override
public List<AppSidebarResponseDto> getAllAppSidebar(){

	// Fetch all AppSidebar entities with their associated AppIcon
    List<AppSidebar> appSidebars = appSidebarRepository.findAllWithAppIcon();

    // Convert AppSidebar entities to AppSidebarResponseDto
    return appSidebars.stream()
            .map(appSidebar -> {
                AppSidebarResponseDto appSidebarResponseDto = new AppSidebarResponseDto();
                appSidebarResponseDto.setAppIcon(appSidebar.getAppIcon()); // Use the appIcon from the entity
                appSidebarResponseDto.setCode(appSidebar.getCode());
                appSidebarResponseDto.setId(appSidebar.getId());
                appSidebarResponseDto.setIsActive(appSidebar.getIsActive());
                appSidebarResponseDto.setLabel(appSidebar.getLabel());
                appSidebarResponseDto.setSortOrder(appSidebar.getSortOrder());
                return appSidebarResponseDto;
            })
            .collect(Collectors.toList());
		}

  @Override
  @Transactional
  public String deleteAppSidebar(Long appSidebarId) {
    log.info(">> deleteAppScreen({})", appSidebarId);
    AppSidebar appSidebar =appSidebarRepository.findById(appSidebarId).get();
    appSidebarRepository.deleteById(appSidebarId);
    return "deleted";
  }

@Override
@Transactional
public void activateAppSidebar(Long appSidebarId) {
	AppSidebar appSidebar=appSidebarRepository.findById(appSidebarId).get();
	appSidebar.setIsActive(true);
}

@Override
public void deactivateAppSidebar(Long appSidebarId) {
	AppSidebar appSidebar=appSidebarRepository.findById(appSidebarId).get();
	appSidebar.setIsActive(false);
}

@Override
@Transactional
public AppSidebarResponseDto updateAppSidebar(Long appSidebarId, AppSidebarRequestDto appSidebarRequestDto){
  log.info(">> updateAppScreen({}, {})", appSidebarId, appSidebarRequestDto);
	AppSidebar appSidebar=appSidebarRepository.findById(appSidebarId).get();
	appSidebar.setIsActive(appSidebarRequestDto.getIsActive());
	appSidebarRepository.save(appSidebar);
  
	AppSidebarResponseDto appSidebarResponseDto=new AppSidebarResponseDto();
	appSidebarResponseDto.setIsActive(appSidebar.getIsActive());
	appSidebarResponseDto.setId(appSidebarId);
	return appSidebarResponseDto;
}
}
