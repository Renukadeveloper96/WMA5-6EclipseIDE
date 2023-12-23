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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.minton.userservice.entities.Engagement;
import com.minton.userservice.entities.Question;
import com.minton.userservice.payload.DeleteRequest;
import com.minton.userservice.payload.EngagementRequest;
import com.minton.userservice.payload.QuestionRequest;
import com.minton.userservice.security.CurrentUser;
import com.minton.userservice.security.UserPrincipal;
import com.minton.userservice.service.EngagementService;
import com.minton.userservice.service.QuestionService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/engagement/{engagementId}/questions")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
@OpenAPIDefinition(info = @Info(title = "Question Controller", version = "v3.3", description = "Question Management Service"))
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@GetMapping
	public ResponseEntity<List<Question>> getQuestionsByEngagementId(@PathVariable Long engagementId) {
		log.info(">> getQuestionsByEngagementId({})", engagementId);
		return ResponseEntity.ok(questionService.getQuestionsByEngagementId(engagementId));
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createQuestion(@PathVariable Long engagementId,
			@Valid @RequestBody QuestionRequest questionRequest, @CurrentUser UserPrincipal currentUser) {
		log.info(">> createQuestion({})", questionRequest);
		return ResponseEntity.ok(questionService.createQuestion(engagementId, questionRequest, currentUser.getId()));
	}

	@PutMapping("/{questionId}")
	public ResponseEntity<Map<String, Object>> updateQuestion(@PathVariable Long engagementId,
			@PathVariable Long questionId, @Valid @RequestBody QuestionRequest questionRequest,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> updateQuestion({}, {})", engagementId, questionRequest);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "isSuccess", Boolean.TRUE,
				"message",
				questionService.updateQuestion(engagementId, questionId, questionRequest, currentUser.getId()));
		return ResponseEntity.ok(map);
	}

	@DeleteMapping("/{questionId}")
	public ResponseEntity<Map<String, Object>> deleteQuestion(@PathVariable Long engagementId,
			@PathVariable Long questionId) {
		log.info(">> deleteQuestion({})", engagementId);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "isSuccess", Boolean.TRUE,
				"message", questionService.deleteQuestion(engagementId, questionId));
		return ResponseEntity.ok(map);
	}

	@PostMapping("/delete")
	public ResponseEntity<Map<String, Object>> deleteQuestions(@PathVariable Long engagementId,
			@RequestBody DeleteRequest deleteRequest) {
		log.info(">> deleteQuestions({})", deleteRequest);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "isSuccess", Boolean.TRUE,
				"message", questionService.deleteQuestions(engagementId, deleteRequest));
		return ResponseEntity.ok(map);
	}

	@PostMapping("/import")
	public ResponseEntity<Map<String, Object>> importQuestions(@PathVariable Long engagementId,
			@RequestParam("file") MultipartFile file, @CurrentUser UserPrincipal currentUser) {
		Map<String, Object> map = Map.of("statusCode", HttpStatus.CREATED + " ", "isSuccess", Boolean.TRUE, "message",
				questionService.importQuestions(engagementId, file, currentUser.getId()));
		return ResponseEntity.ok(map);
	}

}
