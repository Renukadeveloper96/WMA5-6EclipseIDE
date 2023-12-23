package com.minton.userservice.service;

import java.util.List;
import java.util.Map;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minton.userservice.entities.Engagement;
import com.minton.userservice.entities.Promotion;
import com.minton.userservice.entities.User;
import com.minton.userservice.exception.ResourceNotFoundException;
import com.minton.userservice.payload.DeleteRequest;
import com.minton.userservice.payload.PromotionRequest;
import com.minton.userservice.repository.EngagementRepository;
import com.minton.userservice.repository.PromotionRepository;
import com.minton.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PromotionService {

	@Autowired
	private EngagementRepository engagementRepository;

	@Autowired
	private PromotionRepository promotionRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<Promotion> getPromotionsByEngagementId(Long engagementId) {
		log.info(">> getPromotionsByEngagementId({})", engagementId);
		return promotionRepository.findAllByEngagementId(engagementId);
	}

	@Transactional
	public Map<String, Object> createPromotion(Long engagementId, PromotionRequest promotionRequest, Long userId) {
		log.info(">> createPromotion({})", engagementId, promotionRequest, userId);
		try {
			Promotion promotion = savePromotion(promotionRequest);
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
			Engagement engagement = engagementRepository.findById(engagementId).orElseThrow(
					() -> new ResourceNotFoundException("Engagement Not Found By", "EngagementId", engagementId));
			promotion.setCreatedBy(user);
			promotion.setEngagement(engagement);
			promotion.setModifiedBy(userId);
			promotionRepository.save(promotion);
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage(), "isSuccess", Boolean.FALSE);
		}
		return Map.of("statusCode", HttpStatus.CREATED + " ", "isSuccess", Boolean.TRUE, "message",
				"data has been saved successfully!");
	}

	@Transactional
	public String updatePromotion(Long engagementId, Long promotionId, PromotionRequest promotionRequest, Long userId) {
		log.info(">> updatePromotion({}, {})", engagementId, promotionRequest, userId);
		Promotion promotion = promotionRepository.findByIdAndEngagementId(promotionId, engagementId)
				.orElseThrow(() -> new ResourceNotFoundException("Promotion Not Found By", "PromotionId", promotionId));
		updatePromotion(promotion, promotionRequest);
		return "data has been updated successfully!";
	}

	@Transactional
	public String deletePromotion(Long engagementId, Long promotionId) {
		log.info(">> deletePromotion({}, {})", engagementId);
		Promotion promotion = promotionRepository.findByIdAndEngagementId(promotionId, engagementId)
				.orElseThrow(() -> new ResourceNotFoundException("Promotion Not Found By", "PromotionId", promotionId));
		promotion.setIsActive(false);
		return "data has been removed successfully!";
	}

	private Promotion savePromotion(PromotionRequest promotionRequest) {
		Promotion promotion = new Promotion();
		promotion.setPromotionName(promotionRequest.getPromotionName());
		promotion.setPromotionText(promotionRequest.getPromotionText());
		promotion.setUrl(promotionRequest.getUrl());
		promotion.setImageBase64(promotionRequest.getImageBase64());
		promotion.setImage(promotionRequest.getImage());
		promotion.setTags(promotionRequest.getTags());
		promotion.setCreatedAt(LocalDateTime.now());
		promotion.setModifiedAt(LocalDateTime.now());
		return promotion;
	}

	private Promotion updatePromotion(Promotion promotion, PromotionRequest promotionRequest) {
		promotion.setPromotionName(promotionRequest.getPromotionName());
		promotion.setPromotionText(promotionRequest.getPromotionText());
		promotion.setUrl(promotionRequest.getUrl());
		promotion.setImage(promotionRequest.getImage());
		promotion.setImageBase64(promotionRequest.getImageBase64());
		promotion.setTags(promotionRequest.getTags());
		promotion.setCreatedAt(LocalDateTime.now());
		promotion.setModifiedAt(LocalDateTime.now());
		return promotion;
	}

	@Transactional
	public String deletePromotions(Long engagementId, DeleteRequest deleteRequest) {
		log.info(">> deletePromotions({}, {})", engagementId, deleteRequest);
		deleteRequest.getIds().forEach(promotionId -> {
			Promotion promotion = promotionRepository.findByIdAndEngagementId(promotionId, engagementId).orElseThrow(
					() -> new ResourceNotFoundException("Promotion Not Found By", "PromotionId", promotionId));
			promotion.setIsActive(false);
		});
		return "data has been removed successfully!";
	}
}
