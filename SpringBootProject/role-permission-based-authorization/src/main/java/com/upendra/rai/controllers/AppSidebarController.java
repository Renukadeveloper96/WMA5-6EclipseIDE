package com.upendra.rai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upendra.rai.dtos.AnnouncementTopicResponseDto;
import com.upendra.rai.dtos.AppScreenRequestDto;
import com.upendra.rai.dtos.AppScreenResponseDto;
import com.upendra.rai.dtos.AppSidebarRequestDto;
import com.upendra.rai.dtos.AppSidebarResponseDto;
import com.upendra.rai.services.AppSidebarService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/appSidebars")
public class AppSidebarController {

  @Autowired
  private AppSidebarService appSidebarService;

  @PostMapping(value="/{id}")
  public	AppSidebarResponseDto createAppSidebar(@PathVariable("id") Long id,@RequestBody AppSidebarRequestDto appSidebarRequestDto) {
	return appSidebarService.createAppSidebar(id, appSidebarRequestDto);
  }
  
  @GetMapping(value="/{appSidebarId}")
  public AppSidebarResponseDto getAppSidebarById(@RequestHeader("appIconId") Long appIconId, @PathVariable("appSidebarId")Long appSidebarId) {
		return appSidebarService.getAppSidebarById(appIconId, appSidebarId);
	}
  
  @GetMapping
  public List<AppSidebarResponseDto> getAllAnnouncementTopic(@RequestHeader("appIconId") Long appIconId){
		return appSidebarService.getAllAnnouncementTopic(appIconId);
	}
  @GetMapping(value="/getAllAppsidebar")
  public List<AppSidebarResponseDto> getAllAppSidebar( ) {
	  return appSidebarService.getAllAppSidebar();
  }
  
  @DeleteMapping("/{appSidebarId}")
  public String deleteAppSidebar(@PathVariable("appSidebarId")Long appSidebarId) {
		return appSidebarService.deleteAppSidebar(appSidebarId);
	}

  @PutMapping("/{appSidebarId}")
  public AppSidebarResponseDto updateAppSidebar(@PathVariable("appSidebarId") Long appSidebarId,@RequestBody AppSidebarRequestDto appSidebarRequestDto) {
    log.info(">> updateAppScreen({}, {})", appSidebarId, appSidebarRequestDto);
      return appSidebarService.updateAppSidebar(appSidebarId, appSidebarRequestDto);
  }

  @PatchMapping("/{appSidebarId}/activate")
  public void activateAppSidebar(@PathVariable Long appSidebarId) {
    log.info(">> activateAppType({})", appSidebarId);
    appSidebarService.activateAppSidebar(appSidebarId);
  }

  @PatchMapping("/{appSidebarId}/deactivate")
  public void deactivateAppSidebar(@PathVariable Long appSidebarId) {
    log.info(">> deactivateAppSidebar({})", appSidebarId);
    appSidebarService.deactivateAppSidebar(appSidebarId);
  }


}
