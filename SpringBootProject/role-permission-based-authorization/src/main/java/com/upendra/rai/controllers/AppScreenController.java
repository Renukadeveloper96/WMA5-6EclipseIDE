package com.upendra.rai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upendra.rai.dtos.AppScreenRequestDto;
import com.upendra.rai.dtos.AppScreenResponseDto;
import com.upendra.rai.services.AppScreenService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/appScreens")
public class AppScreenController {

  @Autowired
  private AppScreenService appScreenService;
  
  
 //+++++++++++HTTP METHOD GIVES 201 OK IN POSTMAN+++++++++++++
//  @PostMapping
//  public ResponseEntity<Void> createAppScreen(
//      @Valid @RequestBody AppScreenRequestDto appScreenRequestDto) {
//    log.info(">> createAppScreen({})", appScreenRequestDto);
//    appScreenService.createAppScreen(appScreenRequestDto);
//    return ResponseEntity.status(HttpStatus.CREATED).build();
//  }
  
@PostMapping
public AppScreenResponseDto createAppScreen(@RequestBody AppScreenRequestDto appScreenRequestDto)
    {
	return appScreenService.createAppScreen(appScreenRequestDto);
}

  @GetMapping("/{appScreenId}")
  public AppScreenResponseDto getAppScreenById(@PathVariable("appScreenId") Long appScreenId) {
    return appScreenService.getAppScreenById(appScreenId);
  }

  @PutMapping("/{appScreenId}")
  public AppScreenResponseDto updateAppScreen(@PathVariable("appScreenId") Long appScreenId, @RequestBody AppScreenRequestDto appScreenRequestDto) {
    log.info(">> updateAppScreen({}, {})", appScreenId, appScreenRequestDto);
      return appScreenService.updateAppScreen(appScreenId, appScreenRequestDto);
  }

  @DeleteMapping("/{appScreenId}")
  public void deleteAppScreen(@PathVariable Long appScreenId) {
    log.info(">> deleteAppScreen({})", appScreenId);
   appScreenService.deleteAppScreen(appScreenId);
  }
  
  @PatchMapping("/{appScreenId}/activate")
  public void activateAppScreen(@PathVariable Long appScreenId) {
    log.info(">> activateAppType({})", appScreenId);
    appScreenService.activateAppScreen(appScreenId);
  }

  @PatchMapping("/{appScreenId}/deactivate")
  public void deactivateAppScreen(@PathVariable Long appScreenId) {
    log.info(">> deactivateAppScreen({})", appScreenId);
    appScreenService.deactivateAppScreen(appScreenId);
  }

}
