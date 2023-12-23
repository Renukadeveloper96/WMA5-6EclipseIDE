package com.minton.userservice.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minton.userservice.entities.GroupContacts;
import com.minton.userservice.entities.Setting;
import com.minton.userservice.payload.GroupContactRequestDto;
import com.minton.userservice.payload.UpdateContactRequest;
import com.minton.userservice.security.CurrentUser;
import com.minton.userservice.security.UserPrincipal;
import com.minton.userservice.service.GroupService;
import com.minton.userservice.service.SettingService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/setting")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
@OpenAPIDefinition(info = @Info(title = "Group Controller", version = "v3.3", description = "Group Management Service"))
public class SettingController {

	@Autowired
	private SettingService settingService;

	@PostMapping
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<Map<String, Object>> createSettings(@Valid @RequestBody Setting setting,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> createGroupContacts({})", setting);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.CREATED + " ", "isSuccess", Boolean.TRUE, "message",
				settingService.createSettings(setting, currentUser.getId()));
		return ResponseEntity.ok(map);
	}

	@GetMapping
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<List<Setting>> getSettings(@CurrentUser UserPrincipal currentUser) {
		log.info(">> getGroupContacts()");
		return ResponseEntity.ok(settingService.getSettings(currentUser.getId()));
	}

	@PutMapping("/{settingId}")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<Map<String, Object>> updateSetting(@PathVariable Long settingId, @Valid @RequestBody Setting setting,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> updateSetting({}, {})", settingId, setting);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "response", Boolean.TRUE, "message",
				settingService.updateSetting(settingId, setting, currentUser.getId()));
		return ResponseEntity.ok(map);
	}
}
