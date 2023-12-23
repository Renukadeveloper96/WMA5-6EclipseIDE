package com.minton.userservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minton.userservice.entities.Engagement;
import com.minton.userservice.payload.DeleteRequest;
import com.minton.userservice.payload.EngagementRequest;
import com.minton.userservice.security.CurrentUser;
import com.minton.userservice.security.UserPrincipal;
import com.minton.userservice.service.EngagementService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/engagements")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
@OpenAPIDefinition(info = @Info(title = "Engagement Controller", version = "v3.3", description = "Engagement Management Service"))
public class EngagementController {

	@Autowired
	private EngagementService engagementService;

	@GetMapping
	public ResponseEntity<List<Engagement>> getEngagements() {
		log.info(">> getEngagements()");
		return ResponseEntity.ok(engagementService.getEngagements());
	}

	@GetMapping("/{engagementId}")
	public ResponseEntity<Engagement> getEngagementById(@PathVariable Long engagementId
			) {
		log.info(">> getEngagementById({})", engagementId);
		return ResponseEntity.ok(engagementService.getEngagementById(engagementId));
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createEngagement(@Valid @RequestBody EngagementRequest engagementRequest,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> createEngagement({})", engagementRequest);
		return ResponseEntity.ok(engagementService.createEngagement(engagementRequest, currentUser.getId()));
	}

	@PutMapping("/{engagementId}")
	public ResponseEntity<Map<String, Object>> updateEngagement(@PathVariable Long engagementId,
			@Valid @RequestBody EngagementRequest engagementRequest, @CurrentUser UserPrincipal currentUser) {
		log.info(">> updateEngagement({}, {})", engagementId, engagementRequest);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "isSuccess", Boolean.TRUE,
				"message",
				engagementService.updateEngagement(engagementId, engagementRequest, currentUser.getId()));
		return ResponseEntity.ok(map);
	}

	@DeleteMapping("/{engagementId}")
	public ResponseEntity<Map<String, Object>> deleteEngagement(@PathVariable Long engagementId,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> deleteEngagement({})", engagementId);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "isSuccess", Boolean.TRUE,
				"message", engagementService.deleteEngagement(engagementId, currentUser.getId()));
		return ResponseEntity.ok(map);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Map<String, Object>> deleteEngagements(@RequestBody DeleteRequest deleteRequest,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> deleteEngagements({})", deleteRequest);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "isSuccess", Boolean.TRUE, "message",
				engagementService.deleteEngagements(deleteRequest, currentUser.getId()));
		return ResponseEntity.ok(map);
	}

}
