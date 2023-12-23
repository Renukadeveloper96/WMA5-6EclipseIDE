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
import com.minton.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private UserRepository userRepository;


	public String createNote(Note note, Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		note.setModifiedBy(userId);
		note.setCreatedBy(user);
		note.setCreatedAt(LocalDateTime.now());
		note.setModifiedAt(LocalDateTime.now());
		noteRepository.save(note);
		return "data has been saved successfully!";
	}

	public List<Note> getNotes(Long userId) {
		return noteRepository.findAllByCreatedAtDESC(userId);
	}

}
