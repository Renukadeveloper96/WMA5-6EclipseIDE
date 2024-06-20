package com.upendra.rai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upendra.rai.dtos.AppVersionRequestDto;
import com.upendra.rai.dtos.AppVersionResponseDto;
import com.upendra.rai.services.AppVersionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/appVersions")
public class AppVersionController {

  @Autowired
  private AppVersionService appVersionService;

  @PostMapping
  public AppVersionResponseDto createAppVersion(@RequestBody AppVersionRequestDto appVersionRequestDto) {
    log.info(">> createAppVersion({})", appVersionRequestDto);
    return appVersionService.createAppVersion(appVersionRequestDto);
  }
  
//  @GetMapping
//  public AppVersionResponseDto getAppVersionById(@RequestHeader String appVersionId) {
//	  return appVersionService.getAppVersionById(appVersionId);
//  }
  @GetMapping("/{appVersionId}")
  public AppVersionResponseDto getAppVersionById(
      @PathVariable Long appVersionId) {
    log.info(">> getAppVersionById({})", appVersionId);
   return appVersionService.getAppVersionById(appVersionId);
  }
  
  @GetMapping
  public List<AppVersionResponseDto> getAllAppVersion() {
	return appVersionService.getAllAppVersion();
  }
  
  @PutMapping("/{appVersionId}")
  public AppVersionResponseDto updateAppVersion(@PathVariable ("appVersionId") Long appVersionId,@RequestBody AppVersionRequestDto appVersionRequestDto) {
	return appVersionService.updateAppVersion(appVersionId, appVersionRequestDto);
	  
  }
  @PatchMapping("/{appVersionId}/activate")
  public void activateAppVersion(@PathVariable("appVersionId")Long appVersionId) {
    log.info(">> activateAppType({})", appVersionId);
    appVersionService.activateAppVersion(appVersionId);
  }
  
  @PatchMapping("/{appVersionId}/deactivate")
  public void deactivateAppVersion(@PathVariable("appVersionId")  Long appVersionId) {
	  appVersionService.deactivateAppVersion(appVersionId);
  }

}