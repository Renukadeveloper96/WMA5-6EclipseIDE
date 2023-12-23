package com.minton.userservice.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.minton.userservice.entities.Contact;
import com.minton.userservice.entities.Engagement;
import com.minton.userservice.entities.Promotion;
import com.minton.userservice.entities.Question;
import com.minton.userservice.entities.User;
import com.minton.userservice.exception.ResourceNotFoundException;
import com.minton.userservice.payload.ContactImportRequestDto;
import com.minton.userservice.payload.DeleteRequest;
import com.minton.userservice.payload.QuestionImportRequest;
import com.minton.userservice.payload.QuestionRequest;
import com.minton.userservice.repository.EngagementRepository;
import com.minton.userservice.repository.QuestionRepository;
import com.minton.userservice.repository.UserRepository;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuestionService {

	@Autowired
	private EngagementRepository engagementRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<Question> getQuestionsByEngagementId(Long engagementId) {
		log.info(">> getQuestionsByEngagementId({})", engagementId);
		return questionRepository.findAllByEngagementId(engagementId);
	}

	@Transactional
	public Map<String, Object> createQuestion(Long engagementId, QuestionRequest questionRequest, Long userId) {
		log.info(">> createQuestion({})", engagementId, questionRequest, userId);
		try {
			Question question = saveQuestion(questionRequest);
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
			Engagement engagement = engagementRepository.findById(engagementId).orElseThrow(
					() -> new ResourceNotFoundException("Engagement Not Found By", "EngagementId", engagementId));
			question.setCreatedBy(user);
			question.setEngagement(engagement);
			question.setModifiedBy(userId);
			questionRepository.save(question);
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage(), "", Boolean.FALSE);
		}
		return Map.of("statusCode", HttpStatus.CREATED + " ", "isSuccess", Boolean.TRUE, "message",
				"data has been saved successfully!");
	}

	@Transactional
	public String updateQuestion(Long engagementId, Long questionId, QuestionRequest questionRequest, Long userId) {
		log.info(">> updateQuestion({}, {})", engagementId, questionRequest, userId);
		Question question = questionRepository.findByIdAndEngagementId(questionId, engagementId).orElseThrow(
				() -> new ResourceNotFoundException("Engagement Not Found By", "EngagementId", engagementId));
		updateQuestion(question, questionRequest);
		return "data has been updated successfully!";
	}

	@Transactional
	public String deleteQuestion(Long engagementId, Long questionId) {
		log.info(">> deleteQuestion({}, {})", engagementId);
		Question question = questionRepository.findByIdAndEngagementId(questionId, engagementId).orElseThrow(
				() -> new ResourceNotFoundException("Engagement Not Found By", "EngagementId", engagementId));
		question.setIsActive(false);
		return "data has been removed successfully!";
	}

	@Transactional
	public String deleteQuestions(Long engagementId, DeleteRequest deleteRequest) {
		log.info(">> deleteQuestions({}, {})", engagementId, deleteRequest);
		deleteRequest.getIds().forEach(questionId -> {
			Question question = questionRepository.findByIdAndEngagementId(questionId, engagementId).orElseThrow(
					() -> new ResourceNotFoundException("Engagement Not Found By", "EngagementId", engagementId));
			question.setIsActive(false);
		});
		return "data has been removed successfully!";
	}

	@Transactional
	public String importQuestions(Long engagementId, MultipartFile file, Long userId) {
		log.info(">> importQuestions({}, {}, {})", engagementId, file, userId);
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
			List<QuestionImportRequest> questions = new CsvToBeanBuilder<QuestionImportRequest>(bufferedReader)
					.withType(QuestionImportRequest.class).withSkipLines(1).build().parse();
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
			Engagement engagement = engagementRepository.findById(engagementId).orElseThrow(
					() -> new ResourceNotFoundException("Engagement Not Found By", "EngagementId", engagementId));
			questions.forEach(question -> {
				Optional<Question> checkQuestion = questionRepository.findByIdAndQuestionName(engagement.getId(),
						question.getQuestion());
				if (checkQuestion.isEmpty()) {
					Question setQuestion = new Question();
					setQuestion.setCreatedBy(user);
					setQuestion.setModifiedBy(user.getId());
					setQuestion.setEngagement(engagement);
					Question importQuestion = saveImportQuestion(setQuestion, question);
					questionRepository.save(importQuestion);
				}
			});
		} catch (Exception e) {
			throw new ResourceNotFoundException("Some Problem While Importing Data", "", "");
		}
		return "data has been imported successfully!";
	}

	private Question saveQuestion(QuestionRequest questionRequest) {
		Question question = new Question();
		question.setQuestion(questionRequest.getQuestion());
		question.setChoice1(questionRequest.getChoice1());
		question.setChoice2(questionRequest.getChoice2());
		question.setChoice3(questionRequest.getChoice3());
		question.setCorrectChoice(questionRequest.getCorrectChoice());
		question.setTidbitLink(questionRequest.getTidbitLink());
		question.setTidbitText(questionRequest.getTidbitText());
		question.setTags(questionRequest.getTags());
		question.setCreatedAt(LocalDateTime.now());
		question.setModifiedAt(LocalDateTime.now());
		return question;
	}

	private Question updateQuestion(Question question, QuestionRequest questionRequest) {
		question.setQuestion(questionRequest.getQuestion());
		question.setChoice1(questionRequest.getChoice1());
		question.setChoice2(questionRequest.getChoice2());
		question.setChoice3(questionRequest.getChoice3());
		question.setCorrectChoice(questionRequest.getCorrectChoice());
		question.setTidbitLink(questionRequest.getTidbitLink());
		question.setTidbitText(questionRequest.getTidbitText());
		question.setTags(questionRequest.getTags());
		question.setCreatedAt(LocalDateTime.now());
		question.setModifiedAt(LocalDateTime.now());
		return question;
	}

	private Question saveImportQuestion(Question question, QuestionImportRequest questionRequest) {
		question.setQuestion(questionRequest.getQuestion());
		question.setChoice1(questionRequest.getChoice1());
		question.setChoice2(questionRequest.getChoice2());
		question.setChoice3(questionRequest.getChoice3());
		question.setCorrectChoice(questionRequest.getCorrectChoice());
		question.setTidbitLink(questionRequest.getTidbitLink());
		question.setTidbitText(questionRequest.getTidbitText());
		question.setTags(questionRequest.getTags());
		question.setCreatedAt(LocalDateTime.now());
		question.setModifiedAt(LocalDateTime.now());
		return question;
	}
}
