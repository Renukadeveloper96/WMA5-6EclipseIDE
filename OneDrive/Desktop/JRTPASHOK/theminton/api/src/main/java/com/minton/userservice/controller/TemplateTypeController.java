package com.minton.userservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.minton.userservice.entities.TemplateType;
import com.minton.userservice.payload.PagedResponseDto;
import com.minton.userservice.security.CurrentUser;
import com.minton.userservice.security.UserPrincipal;
import com.minton.userservice.service.TemplateTypeService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.data.domain.Pageable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/templateTypes")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
@OpenAPIDefinition(info = @Info(title = "TemplateType Controller", version = "v3.3", description = "TemplateType Management Service"))
public class TemplateTypeController {

	@Autowired
	private TemplateTypeService engagementTypeService;

	@GetMapping
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<List<TemplateType>> getTemplateTypes(@CurrentUser UserPrincipal currentUser) {
		log.info(">> getTemplateTypes()");
		return ResponseEntity.ok(engagementTypeService.getTemplateTypes(currentUser.getId()));
	}

	@GetMapping("/{templateTypeId}")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<TemplateType> getTemplateTypeById(@PathVariable Long templateTypeId,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> getTemplateTypeById({})", templateTypeId);
		return ResponseEntity.ok(engagementTypeService.getTemplateTypeById(templateTypeId, currentUser.getId()));
	}

	@PostMapping
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<Map<String, Object>> createTemplateType(@Valid @RequestBody TemplateType templateType,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> createTemplateType({})", templateType);
		return ResponseEntity.ok(engagementTypeService.createTemplateType(templateType, currentUser.getId()));
	}

	@PutMapping("/{templateTypeId}")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<Map<String, Object>> updateTemplateType(@PathVariable Long templateTypeId,
			@Valid @RequestBody TemplateType templateType, @CurrentUser UserPrincipal currentUser) {
		log.info(">> updateTemplateType({}, {})", templateTypeId, templateType);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "isSuccess", Boolean.TRUE,
				"message", engagementTypeService.updateTemplateType(templateTypeId, templateType, currentUser.getId()));
		return ResponseEntity.ok(map);
	}

	@DeleteMapping("/{templateTypeId}")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<Map<String, Object>> deleteTemplateType(@PathVariable Long templateTypeId,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> deleteTemplateType({})", templateTypeId);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "isSuccess", Boolean.TRUE,
				"message",
				engagementTypeService.deleteTemplateType(templateTypeId, currentUser.getId()));
		return ResponseEntity.ok(map);
	}

}
