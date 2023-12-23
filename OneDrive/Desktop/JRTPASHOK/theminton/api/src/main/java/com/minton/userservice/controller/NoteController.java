package com.minton.userservice.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minton.userservice.entities.GroupContacts;
import com.minton.userservice.entities.Note;
import com.minton.userservice.payload.GroupContactRequestDto;
import com.minton.userservice.security.CurrentUser;
import com.minton.userservice.security.UserPrincipal;
import com.minton.userservice.service.GroupService;
import com.minton.userservice.service.NoteService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/note")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
@OpenAPIDefinition(info = @Info(title = "Group Controller", version = "v3.3", description = "Group Management Service"))
public class NoteController {

	@Autowired
	private NoteService noteService;

	@PostMapping
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<Map<String, Object>> createNote(@Valid @RequestBody Note note,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> createGroupContacts({})", note);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.CREATED + " ", "isSuccess", Boolean.TRUE, "message",
				noteService.createNote(note, currentUser.getId()));
		return ResponseEntity.ok(map);
	}

	@GetMapping
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<List<Note>> getGroupContacts(@CurrentUser UserPrincipal currentUser) {
		log.info(">> getGroupContacts()");
		return ResponseEntity.ok(noteService.getNotes(currentUser.getId()));
	}
}
