package com.upendra.rai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upendra.rai.dtos.AppIconRequestDto;
import com.upendra.rai.dtos.AppIconResponseDto;
import com.upendra.rai.dtos.ClientRequestDto;
import com.upendra.rai.dtos.ClientResponseDto;
import com.upendra.rai.services.AppIconService;

@RestController
@RequestMapping("/v1/appIcons")
public class AppIconController {

  @Autowired
  private AppIconService appIconService;
  
  @PostMapping
  public AppIconResponseDto createAppIcon(@RequestBody AppIconRequestDto appIconRequestDto) {
	  return appIconService.createAppIcon(appIconRequestDto);
  }
  @GetMapping
  @Transactional(readOnly = true)
  public List<AppIconResponseDto> getAppIcon(){
	  return appIconService.getAppIcon();
  }
  
  @GetMapping(value="/{id}")
  @Transactional(readOnly = true)
  public AppIconResponseDto getAppIconById(@PathVariable("id")Long id) {
	  return appIconService.getAppIconById(id);																																				
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<AppIconResponseDto> updateAppIconById(@PathVariable Long id, @RequestBody AppIconRequestDto appIconRequestDto) {
      AppIconResponseDto updatedAppIcon = appIconService.updateAppIconById(id,appIconRequestDto);
      return ResponseEntity.ok(updatedAppIcon);
  }
  @DeleteMapping(value="/{id}")
  public String deleteAppIconById(@PathVariable("id")Long id) {
	  return appIconService.deleteAppIconById(id);
  }
}
