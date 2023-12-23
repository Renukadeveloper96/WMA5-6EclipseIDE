package com.minton.userservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.minton.userservice.entities.Contact;
import com.minton.userservice.payload.ContactRequestDto;
import com.minton.userservice.payload.ContactResponseDto;
import com.minton.userservice.payload.DeleteContactRequest;
import com.minton.userservice.payload.GroupContactRequestDto;
import com.minton.userservice.payload.PagedResponseDto;
import com.minton.userservice.payload.UpdateContactRequest;
import com.minton.userservice.security.CurrentUser;
import com.minton.userservice.security.UserPrincipal;
import com.minton.userservice.service.ContactService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.data.domain.Pageable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/contacts")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
@OpenAPIDefinition(info = @Info(title = "Contact Controller", version = "v3.3", description = "Contact Management Service"))
public class ContactController {

	@Autowired
	private ContactService contactService;

	@GetMapping
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<PagedResponseDto<ContactResponseDto>> getContacts(Pageable pageable,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> getContacts()");
		return ResponseEntity.ok(contactService.getContacts(pageable, currentUser.getId()));
	}

	@GetMapping("/{contactId}")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<ContactResponseDto> getContactById(@PathVariable Long contactId,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> getContactById({})", contactId);
		return ResponseEntity.ok(contactService.getContactById(contactId, currentUser.getId()));
	}

	@PostMapping
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<Map<String, Object>> createContact(@Valid @RequestBody ContactRequestDto contactRequestDto,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> createContact({})", contactRequestDto);
		return ResponseEntity.ok(contactService.createContact(contactRequestDto, currentUser.getId()));
	}

	@PutMapping("/{contactId}")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<Map<String, Object>> updateContact(@PathVariable Long contactId,
			@Valid @RequestBody UpdateContactRequest updateContactRequest, @CurrentUser UserPrincipal currentUser) {
		log.info(">> updateContact({}, {})", contactId, updateContactRequest);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "isSuccess", Boolean.TRUE, "message",
				contactService.updateContact(contactId, updateContactRequest, currentUser.getId()));
		return ResponseEntity.ok(map);
	}

	@PostMapping("/delete")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
	public ResponseEntity<Map<String, Object>> deleteContact(@RequestBody DeleteContactRequest deleteContactRequest,
			@CurrentUser UserPrincipal currentUser) {
		log.info(">> deleteContact({})", deleteContactRequest);
		Map<String, Object> map = Map.of("statusCode", HttpStatus.NO_CONTENT + " ", "isSuccess", Boolean.TRUE, "message",
				contactService.deleteContact(deleteContactRequest, currentUser.getId()));
		return ResponseEntity.ok(map);
	}

	@PostMapping("/import")
	public ResponseEntity<Map<String, Object>> importContacts(@RequestParam("file") MultipartFile file,
			@CurrentUser UserPrincipal currentUser) {
		Map<String, Object> map = Map.of("statusCode", HttpStatus.CREATED + " ", "isSuccess", Boolean.TRUE, "message",
				contactService.importContacts(file, currentUser.getId()));
		return ResponseEntity.ok(map);
	}

	@GetMapping("/download/csv")
	public ResponseEntity<Resource> downloadCsv() throws IOException {
		// Load the CSV file from the resources folder
		String filename = "ContactUploadFiles.csv";
		Resource resource = new ClassPathResource(filename);

		// Determine the content type dynamically based on the file extension
		String contentType = "text/csv"; // Default content type for CSV
		if (filename.endsWith(".xlsx")) {
			contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"").body(resource);
	}

}
