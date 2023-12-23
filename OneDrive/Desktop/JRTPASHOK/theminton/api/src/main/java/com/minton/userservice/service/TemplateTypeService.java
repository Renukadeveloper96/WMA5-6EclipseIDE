package com.minton.userservice.service;

import java.util.List;
import java.util.Map;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minton.userservice.entities.TemplateType;
import com.minton.userservice.entities.User;
import com.minton.userservice.exception.ResourceNotFoundException;
import com.minton.userservice.repository.TemplateTypeRepository;
import com.minton.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TemplateTypeService {

	@Autowired
	private TemplateTypeRepository templateTypeRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<TemplateType> getTemplateTypes(Long userId) {
		log.info(">> getTemplateTypes()");
		return templateTypeRepository.findAll();
	}

	@Transactional(readOnly = true)
	public TemplateType getTemplateTypeById(Long engagementTypeId, Long userId) {
		log.info(">> getTemplateTypeById({}, {}, {})", engagementTypeId);
		return templateTypeRepository.findActiveById(engagementTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("TemplateType Not Found By ", "", engagementTypeId));
	}

	@Transactional
	public Map<String, Object> createTemplateType(TemplateType engagementType, Long userId) {
		log.info(">> createTemplateType({})", engagementType, userId);
		try {
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User Not Found By", "userId", userId));
			engagementType.setCreatedAt(LocalDateTime.now());
			engagementType.setModifiedAt(LocalDateTime.now());
			engagementType.setCreatedBy(user);
			engagementType.setModifiedBy(user.getId());
			templateTypeRepository.save(engagementType);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Please Enter Unique type", e.getMessage(), Boolean.FALSE);
		}

		return Map.of("statusCode", HttpStatus.CREATED + " ", "isSuccess", Boolean.TRUE, "message",
				"data has been saved successfully!");
	}

	@Transactional
	public String updateTemplateType(Long engagementTypeId, TemplateType engagementType, Long userId) {
		log.info(">> updateTemplateType({}, {})", engagementTypeId, engagementType);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		TemplateType saveTemplateType = templateTypeRepository.findActiveById(engagementTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("TemplateType", "id", engagementTypeId));
		saveTemplateType.setType(engagementType.getType());
		saveTemplateType.setModifiedBy(user.getId());
		saveTemplateType.setModifiedAt(LocalDateTime.now());
		templateTypeRepository.save(saveTemplateType);
		return "data has been updated successfully!";
	}

	@Transactional
	public String deleteTemplateType(Long engagementTypeId, Long userId) {
		log.info(">> deleteTemplateType({}, {})", engagementTypeId, userId);
		TemplateType engagementType = templateTypeRepository.findById(engagementTypeId)
				.orElseThrow(() -> new ResourceNotFoundException("TemplateType", "id", engagementTypeId));
		engagementType.setIsActive(false);
		return "data has been removed successfully!";
	}

}
