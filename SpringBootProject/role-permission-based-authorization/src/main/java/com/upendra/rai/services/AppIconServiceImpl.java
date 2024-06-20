package com.upendra.rai.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upendra.rai.dtos.AppIconRequestDto;
import com.upendra.rai.dtos.AppIconResponseDto;
import com.upendra.rai.entities.AppIcon;
import com.upendra.rai.repositories.AppIconRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppIconServiceImpl implements AppIconService {

  @Autowired
  private AppIconRepository appIconRepository;

  @Autowired
	private ModelMapper modelMapper;
  
  @Override
  @Transactional
  public AppIconResponseDto createAppIcon(AppIconRequestDto appIconRequestDto)  {
    log.info(">> createClient({}, {})", appIconRequestDto); 
    AppIcon appIcon=modelMapper.map(appIconRequestDto, AppIcon.class);
    AppIcon savedAppIcon=appIconRepository.save(appIcon);
    return modelMapper.map(savedAppIcon, AppIconResponseDto.class);
  }
 
  @Override
  @Transactional(readOnly = true)
  public List<AppIconResponseDto> getAppIcon() {
    log.info(">> getClients({}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {})");
    List<AppIcon> appIcons=appIconRepository.findAll();
    return appIcons.stream().map(appIcon->modelMapper.map(appIcon,AppIconResponseDto.class)).collect(Collectors.toList());
    
  }
  
@Override
public AppIconResponseDto getAppIconById(Long id) {
	AppIcon appIcon=appIconRepository.findById(id).get();
	return modelMapper.map(appIcon, AppIconResponseDto.class);
}

@Transactional
@Override
public AppIconResponseDto updateAppIconById( Long id,AppIconRequestDto appIconRequestDto) {
    // Find the existing AppIcon by id
    AppIcon appIcon = appIconRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("AppIcon not found with id: " + id));

    // Map the fields from appIconRequestDto to the existing AppIcon entity
    modelMapper.map(appIconRequestDto, appIcon);

    // Save the updated AppIcon entity
    AppIcon updatedAppIcon = appIconRepository.save(appIcon);

    // Convert the updated AppIcon entity to AppIconResponseDto
    return modelMapper.map(updatedAppIcon, AppIconResponseDto.class);
}

@Override
public String deleteAppIconById(Long id) {
	AppIcon appIcon = appIconRepository.findById(id).get();
	appIconRepository.delete(appIcon);
	 return "deleted";
}
}
