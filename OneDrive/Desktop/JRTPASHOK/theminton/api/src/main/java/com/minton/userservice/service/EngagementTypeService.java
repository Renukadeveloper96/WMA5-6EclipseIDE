package com.minton.userservice.service;

import java.util.List;
import java.util.Map;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minton.userservice.entities.EngagementType;
import com.minton.userservice.entities.User;
import com.minton.userservice.exception.ResourceNotFoundException;
import com.minton.userservice.repository.EngagementTypeRepository;
import com.minton.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EngagementTypeService {

	@Autowired
	private EngagementTypeRepository engagementTypeRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<EngagementType> getEngagementTypes() {
		log.info(">> getEngagementTypes()");
		return engagementTypeRepository.findAll();
	}

	@Transactional(readOnly = true)
	public EngagementType getEngagementTypeById(Long engagementTypeId, Long userId) {
		log.info(">> getEngagementTypeById({}, {}, {})", engagementTypeId);
		return engagementTypeRepository.findActiveById(engagementTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("EngagementType Not Found By ", "", engagementTypeId));
	}

	@Transactional
	public Map<String, Object> createEngagementType(EngagementType engagementType, Long userId) {
		log.info(">> createEngagementType({})", engagementType, userId);
		try {
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
			engagementType.setCreatedAt(LocalDateTime.now());
			engagementType.setModifiedAt(LocalDateTime.now());
			engagementType.setCreatedBy(user);
			engagementType.setModifiedBy(user.getId());
			engagementTypeRepository.save(engagementType);
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage(), "", Boolean.FALSE);
		}

		return Map.of("statusCode", HttpStatus.CREATED + " ", "isSuccess", Boolean.TRUE, "message",
				"data has been saved successfully!");
	}

	@Transactional
	public String updateEngagementType(Long engagementTypeId, EngagementType engagementType, Long userId) {
		log.info(">> updateEngagementType({}, {})", engagementTypeId, engagementType);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		EngagementType saveEngagementType = engagementTypeRepository.findActiveById(engagementTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("EngagementType", "id", engagementTypeId));
		saveEngagementType.setType(engagementType.getType());
		saveEngagementType.setModifiedBy(user.getId());
		saveEngagementType.setModifiedAt(LocalDateTime.now());
		engagementTypeRepository.save(saveEngagementType);
		return "data has been updated successfully!";
	}

	@Transactional
	public String deleteEngagementType(Long engagementTypeId) {
		log.info(">> deleteEngagementType({}, {})", engagementTypeId);
		EngagementType engagementType = engagementTypeRepository.findById(engagementTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("EngagementType", "id", engagementTypeId));
		engagementType.setIsActive(false);
		return "data has been removed successfully!";
	}

}
