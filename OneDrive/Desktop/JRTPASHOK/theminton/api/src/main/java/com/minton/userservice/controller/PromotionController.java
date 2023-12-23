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

import com.minton.userservice.entities.Promotion;
import com.minton.userservice.payload.DeleteRequest;
import com.minton.userservice.payload.PromotionRequest;
import com.minton.userservice.security.CurrentUser;
import com.minton.userservice.security.UserPrincipal;
import com.minton.userservice.service.PromotionService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/engagement/{engagementId}/promotions")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
@OpenAPIDefinition(info = @Info(title = "Promotion Controller", version = "v3.3", description = "Promotion Management Service"))
public class PromotionController {

	@Autowired
	private PromotionService promotionService;

	@GetMapping
	public ResponseEntity<List<Promotion>> getPromotionsByPromotionId(@PathVariable Long engagementId) {
		log.info(">> getPromotionsByPromotionId({})", engagementId);
		return ResponseEntity.ok(promotionService.getPromotionsByEngagementId(engagementId));
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createPromotion(@PathVariable Long engagementId,
			@Valid @RequestBody PromotionRequest questionRequest, @CurrentUser UserPrincipal currentUser) {
		log.info(">> createPromotion({})", questionRequest);
		return ResponseEntity.ok(promotionService.createPromotion(engagementId, questionRequest, currentUser.getId()));
	}

	@PutMapping("/{promotionId}")
	public ResponseEntity<Map<String, Object>> updatePromotion(@PathVariable Long engagementId,
			@PathVariable Long promotionId, @Valid @RequestBody PromotionRequest questionRequest,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> updatePromotion({}, {})", engagementId, questionRequest);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "isSuccess", Boolean.TRUE,
				"message",
				promotionService.updatePromotion(engagementId, promotionId, questionRequest, currentUser.getId()));
		return ResponseEntity.ok(map);
	}

	@DeleteMapping("/{promotionId}")
	public ResponseEntity<Map<String, Object>> deletePromotion(@PathVariable Long engagementId,
			@PathVariable Long promotionId) {
		log.info(">> deletePromotion({})", engagementId);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "isSuccess", Boolean.TRUE,
				"message", promotionService.deletePromotion(engagementId, promotionId));
		return ResponseEntity.ok(map);
	}

	@PostMapping("/delete")
	public ResponseEntity<Map<String, Object>> deletePromotions(@PathVariable Long engagementId,
			@RequestBody DeleteRequest deleteRequest) {
		log.info(">> deletePromotions({})", deleteRequest);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "isSuccess", Boolean.TRUE,
				"message", promotionService.deletePromotions(engagementId,deleteRequest));
		return ResponseEntity.ok(map);
	}

}
