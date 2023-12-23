package com.minton.userservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minton.userservice.entities.Contact;
import com.minton.userservice.entities.Group;
import com.minton.userservice.entities.GroupContacts;
import com.minton.userservice.entities.Note;
import com.minton.userservice.entities.Setting;
import com.minton.userservice.entities.User;
import com.minton.userservice.exception.ResourceNotFoundException;
import com.minton.userservice.payload.GroupContactRequestDto;
import com.minton.userservice.payload.GroupRequestDto;
import com.minton.userservice.payload.GroupResponse;
import com.minton.userservice.payload.PagedResponseDto;
import com.minton.userservice.repository.ContactRepository;
import com.minton.userservice.repository.GroupContactRepository;
import com.minton.userservice.repository.GroupRepository;
import com.minton.userservice.repository.NoteRepository;
import com.minton.userservice.repository.SettingRepository;
import com.minton.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SettingService {

	@Autowired
	private SettingRepository settingRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public String createSettings(@Valid Setting setting, Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Optional<Setting> checkSetting = settingRepository.findByUser(user);
		setting.setModifiedBy(userId);
		setting.setCreatedBy(user);
		setting.setCreatedAt(LocalDateTime.now());
		setting.setModifiedAt(LocalDateTime.now());
		if (checkSetting.isPresent()) {
			checkSetting.get().setBillingPlan(setting.getBillingPlan());
			checkSetting.get().setNoOfUsers(setting.getNoOfUsers());
			checkSetting.get().setIsTelegram(setting.getIsTelegram());
			checkSetting.get().setIsWhatsapp(setting.getIsWhatsapp());
			settingRepository.save(checkSetting.get());
		} else {
			settingRepository.save(setting);
		}

		return "data has been saved successfully!";
	}

	public List<Setting> getSettings(Long id) {
		return settingRepository.findAll();
	}

	public String updateSetting(Long settingId, Setting setting, Long userId) {
		Setting updateSetting = settingRepository.findById(settingId)
				.orElseThrow(() -> new ResourceNotFoundException("Setting", "settingId", settingId));
		updateSetting.setNoOfUsers(setting.getNoOfUsers());
		updateSetting.setIsTelegram(setting.getIsTelegram());
		updateSetting.setIsWhatsapp(setting.getIsWhatsapp());
		updateSetting.setModifiedBy(userId);
		updateSetting.setBillingPlan(setting.getBillingPlan());
		updateSetting.setCreatedAt(LocalDateTime.now());
		updateSetting.setModifiedAt(LocalDateTime.now());
		settingRepository.save(updateSetting);
		return "data has been updated successfully!";
	}

}
