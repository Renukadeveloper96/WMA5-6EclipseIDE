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

import com.minton.userservice.entities.EngagementType;
import com.minton.userservice.payload.PagedResponseDto;
import com.minton.userservice.security.CurrentUser;
import com.minton.userservice.security.UserPrincipal;
import com.minton.userservice.service.EngagementTypeService;

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
@RequestMapping("/enagementTypes")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
@OpenAPIDefinition(info = @Info(title = "EngagementType Controller", version = "v3.3", description = "EngagementType Management Service"))
public class EngagementTypeController {

	@Autowired
	private EngagementTypeService engagementTypeService;

	@GetMapping
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<List<EngagementType>> getEngagementTypes() {
		log.info(">> getEngagementTypes()");
		return ResponseEntity.ok(engagementTypeService.getEngagementTypes());
	}

	@GetMapping("/{engagementTypeId}")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<EngagementType> getEngagementTypeById(@PathVariable Long engagementTypeId,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> getEngagementTypeById({})", engagementTypeId);
		return ResponseEntity.ok(engagementTypeService.getEngagementTypeById(engagementTypeId, currentUser.getId()));
	}

	@PostMapping
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<Map<String, Object>> createEngagementType(@Valid @RequestBody EngagementType engagementType,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> createEngagementType({})", engagementType);
		return ResponseEntity.ok(engagementTypeService.createEngagementType(engagementType, currentUser.getId()));
	}

	@PutMapping("/{engagementTypeId}")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<Map<String, Object>> updateEngagementType(@PathVariable Long engagementTypeId,
			@Valid @RequestBody EngagementType engagementType, @CurrentUser UserPrincipal currentUser) {
		log.info(">> updateEngagementType({}, {})", engagementTypeId, engagementType);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "isSuccess", Boolean.TRUE,
				"message",
				engagementTypeService.updateEngagementType(engagementTypeId, engagementType, currentUser.getId()));
		return ResponseEntity.ok(map);
	}

	@DeleteMapping("/{engagementTypeId}")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<Map<String, Object>> deleteEngagementType(@PathVariable Long engagementTypeId
			) {
		log.info(">> deleteEngagementType({})", engagementTypeId);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "isSuccess", Boolean.TRUE,
				"message", engagementTypeService.deleteEngagementType(engagementTypeId));
		return ResponseEntity.ok(map);
	}

}
