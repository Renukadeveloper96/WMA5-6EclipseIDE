package com.upendra.rai.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.upendra.rai.dtos.AppSidebarRequestDto;
import com.upendra.rai.dtos.AppSidebarResponseDto;
import com.upendra.rai.dtos.AppTypeRequestDto;
import com.upendra.rai.dtos.AppTypeResponseDto;
import com.upendra.rai.entities.AppType;
import com.upendra.rai.exceptions.ServiceException;
import com.upendra.rai.services.AppTypeService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/appTypes")
public class AppTypeController {

  @Autowired
  private AppTypeService appTypeService;

  @PostMapping(value="/{id}")
  public	AppTypeResponseDto createAppSidebar(@PathVariable("id") Long id,@RequestBody AppTypeRequestDto appTypeRequestDto) {
	return appTypeService.createAppType(id, appTypeRequestDto);
  }

  @GetMapping("/getAllTypes")
  public List<AppTypeResponseDto> getAppTypeById() {
    log.info(">> getAppTypeById({})");
    return 
        appTypeService.getAllAppType();
  }
  
  @GetMapping(value="/{appTypeId}")
  public AppTypeResponseDto getAppSidebarById(@RequestHeader("appVersionId") Long appVersionId, @PathVariable("appTypeId")Long appTypeId) {
		return appTypeService.getAppTypeById(appVersionId, appTypeId);
	}
  
  @PutMapping("/{appTypeId}")
  public AppTypeResponseDto updateAppType(@PathVariable("appTypeId") Long appTypeId,@RequestBody AppTypeRequestDto appTypeRequestDto) {
    log.info(">> updateAppScreen({}, {})", appTypeId, appTypeRequestDto);
      return appTypeService.updateAppType(appTypeId, appTypeRequestDto);
  }
}
