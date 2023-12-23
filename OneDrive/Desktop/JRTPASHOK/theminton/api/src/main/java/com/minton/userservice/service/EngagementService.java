package com.minton.userservice.service;

import java.util.List;
import java.util.Map;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minton.userservice.entities.Contact;
import com.minton.userservice.entities.Engagement;
import com.minton.userservice.entities.EngagementType;
import com.minton.userservice.entities.TemplateType;
import com.minton.userservice.entities.User;
import com.minton.userservice.exception.ResourceNotFoundException;
import com.minton.userservice.payload.DeleteContactRequest;
import com.minton.userservice.payload.DeleteRequest;
import com.minton.userservice.payload.EngagementRequest;
import com.minton.userservice.repository.EngagementRepository;
import com.minton.userservice.repository.EngagementTypeRepository;
import com.minton.userservice.repository.TemplateTypeRepository;
import com.minton.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EngagementService {

	@Autowired
	private EngagementRepository engagementRepository;

	@Autowired
	private EngagementTypeRepository engagementTypeRepository;

	@Autowired
	private TemplateTypeRepository templateTypeRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<Engagement> getEngagements() {
		log.info(">> getEngagements()");
		return engagementRepository.findActiveAll();
	}

	@Transactional(readOnly = true)
	public Engagement getEngagementById(Long engagementId) {
		log.info(">> getEngagementById({}, {}, {})", engagementId);
		return engagementRepository.findById(engagementId)
				.orElseThrow(() -> new ResourceNotFoundException("Engagement Not Found By ", "", engagementId));
	}

	@Transactional
	public Map<String, Object> createEngagement(EngagementRequest engagementRequest, Long userId) {
		log.info(">> createEngagement({})", engagementRequest, userId);
		try {
			Engagement engagement = new Engagement();
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
			EngagementType engagementType = engagementTypeRepository.findById(engagementRequest.getEngagementTypeId())
					.orElseThrow(() -> new ResourceNotFoundException("EngagementType Not Found By", "EngagementTypeId",
							engagementRequest.getEngagementTypeId()));

			TemplateType templateType = templateTypeRepository.findById(engagementRequest.getTemplateTypeId())
					.orElseThrow(() -> new ResourceNotFoundException("TemplateTypeType Not Found By",
							"TemplateTypeTypeId", engagementRequest.getTemplateTypeId()));
			engagement.setName(engagementRequest.getName());
			engagement.setTemplateType(templateType);
			engagement.setEngagementType(engagementType);
			engagement.setCreatedAt(LocalDateTime.now());
			engagement.setModifiedAt(LocalDateTime.now());
			engagement.setCreatedBy(user);
			engagement.setModifiedBy(user.getId());
			engagementRepository.save(engagement);
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage(), "", Boolean.FALSE);
		}

		return Map.of("statusCode", HttpStatus.CREATED + " ", "isSuccess", Boolean.TRUE, "message",
				"data has been saved successfully!");
	}

	@Transactional
	public String updateEngagement(Long engagementId, EngagementRequest engagementRequest, Long userId) {
		log.info(">> updateEngagement({}, {})", engagementId, engagementRequest);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Engagement engagement = engagementRepository.findById(engagementId).orElseThrow(
				() -> new ResourceNotFoundException("Engagement Not Found By", "EngagementId", engagementId));
		EngagementType engagementType = engagementTypeRepository.findById(engagementRequest.getEngagementTypeId())
				.orElseThrow(() -> new ResourceNotFoundException("EngagementType Not Found By", "EngagementTypeId",
						engagementRequest.getEngagementTypeId()));

		TemplateType templateType = templateTypeRepository.findById(engagementRequest.getTemplateTypeId())
				.orElseThrow(() -> new ResourceNotFoundException("TemplateTypeType Not Found By", "TemplateTypeTypeId",
						engagementRequest.getTemplateTypeId()));
		engagement.setName(engagementRequest.getName());
		engagement.setEngagementType(engagementType);
		engagement.setTemplateType(templateType);
		engagement.setModifiedBy(user.getId());
		engagement.setModifiedAt(LocalDateTime.now());
		engagementRepository.save(engagement);
		return "data has been updated successfully!";
	}

	@Transactional
	public String deleteEngagement(Long engagementId, Long userId) {
		log.info(">> deleteEngagement({}, {})", engagementId, userId);
		Engagement engagement = engagementRepository.findById(engagementId).orElseThrow(
				() -> new ResourceNotFoundException("Engagement Not Found By", "EngagementId", engagementId));
		engagement.setIsActive(false);
		return "data has been removed successfully!";
	}

	@Transactional
	public String deleteEngagements(DeleteRequest deleteRequest, Long userId) {
		log.info(">> deleteEngagements({}, {})", deleteRequest, userId);
		deleteRequest.getIds().forEach(engagementId -> {
			Engagement engagement = engagementRepository.findById(engagementId).orElseThrow(
					() -> new ResourceNotFoundException("Engagement Not Found By", "engagementId", engagementId));
			engagement.setIsActive(false);
		});
		return "data has been removed successfully!";
	}

}
