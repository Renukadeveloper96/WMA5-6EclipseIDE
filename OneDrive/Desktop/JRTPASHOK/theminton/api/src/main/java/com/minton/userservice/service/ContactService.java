package com.minton.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

import com.opencsv.bean.CsvToBeanBuilder;

import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.minton.userservice.entities.Contact;
import com.minton.userservice.entities.Group;
import com.minton.userservice.entities.GroupContacts;
import com.minton.userservice.entities.User;
import com.minton.userservice.exception.ResourceNotFoundException;
import com.minton.userservice.payload.ContactImportRequestDto;
import com.minton.userservice.payload.ContactRequestDto;
import com.minton.userservice.payload.ContactResponseDto;
import com.minton.userservice.payload.DeleteContactRequest;
import com.minton.userservice.payload.GroupResponse;
import com.minton.userservice.payload.PagedResponseDto;
import com.minton.userservice.payload.UpdateContactRequest;
import com.minton.userservice.repository.ContactRepository;
import com.minton.userservice.repository.GroupContactRepository;
import com.minton.userservice.repository.GroupRepository;
import com.minton.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private GroupContactRepository groupContactRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional(readOnly = true)
	public PagedResponseDto<ContactResponseDto> getContacts(Pageable pageable, Long userId) {
		log.info(">> getContacts()");
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Page<Contact> contactPage = contactRepository.findAllAndUser(pageable, user);
		PagedResponseDto<ContactResponseDto> pageDto = new PagedResponseDto<ContactResponseDto>();
		pageDto.setPage(contactPage.getNumber());
		pageDto.setPageSize(contactPage.getSize());
		pageDto.setTotalCount(contactPage.getTotalElements());
		List<ContactResponseDto> list = contactPage.getContent().stream().map(contact -> {
			ContactResponseDto setdata = modelMapper.map(contact, ContactResponseDto.class);
			List<GroupResponse> groups = new ArrayList<>();
			GroupResponse group = new GroupResponse();
			List<GroupContacts> contacts = groupContactRepository.findByContact(setdata.getId());
			if (CollectionUtils.isNotEmpty(contacts)) {
				GroupContacts setContact = contacts.get(0);
				group.setId(setContact.getGroup().getId());
				group.setGroupName(setContact.getGroup().getGroupName());
				group.setIsActive(setContact.getGroup().getIsActive());
				group.setCreatedOn(setContact.getGroup().getCreatedOn());
				group.setCreatedBy(setContact.getGroup().getCreatedBy());
				group.setModifiedBy(setContact.getGroup().getModifiedBy());
				group.setModifiedAt(setContact.getGroup().getModifiedAt());
				groups.add(group);
				setdata.setGroupResponses(groups);
			}
			return setdata;
		}).collect(Collectors.toList());

		pageDto.setList(list);
		return pageDto;
	}

	@Transactional(readOnly = true)
	public ContactResponseDto getContactById(Long contactId, Long userId) {
		log.info(">> getContactById({}, {}, {})", contactId);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Contact contact = contactRepository.findActiveByIdAndUserId(contactId, user)
				.orElseThrow(() -> new ResourceNotFoundException("Contact Not Found By ", "", ""));
		return modelMapper.map(contact, ContactResponseDto.class);
	}

	@Transactional
	public Map<String, Object> createContact(ContactRequestDto contactRequestDto, Long userId) {
		log.info(">> createContact({})", contactRequestDto, userId);
		try {
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
			Optional<Contact> contact = contactRepository.findByPhone(contactRequestDto.getPrimaryNumber(), user);
			if (contact.isPresent()) {
				throw new ResourceNotFoundException("Contact is Already Exist in Database", "", contact.get());
			}
			contact = Optional.ofNullable(new Contact());
			contact = Optional.ofNullable(updatedContact(contact.get(), contactRequestDto));
			contact.get().setCreatedBy(user);
			contact.get().setModifiedBy(user.getId());
			contactRepository.save(contact.get());
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage(), "", Boolean.FALSE);
		}

		return Map.of("statusCode", HttpStatus.CREATED + " ", "isSuccess", Boolean.TRUE, "message",
				"data has been saved successfully!");
	}

	@Transactional
	public String updateContact(Long contactId, UpdateContactRequest updateContactRequest, Long userId) {
		log.info(">> updateContact({}, {})", contactId, updateContactRequest);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Contact contact = contactRepository.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact", "id", contactId));
		contact = updateContact(contact, updateContactRequest);
		contact.setModifiedBy(user.getId());
		contact.setModifiedAt(LocalDateTime.now());
		contactRepository.save(contact);
		return "data has been updated successfully!";
	}

	@Transactional
	public String deleteContact(DeleteContactRequest deleteContactRequest, Long userId) {
		log.info(">> deleteContact({}, {})", deleteContactRequest);
		deleteContactRequest.getContactIds().forEach(contactId -> {
			Contact contact = contactRepository.findByIdAndUserId(contactId, userId)
					.orElseThrow(() -> new ResourceNotFoundException("Contact", "id", contactId));
			contact.setIsActive(false);
		});
		return "data has been removed successfully!";
	}

	@Transactional
	public String importContacts(MultipartFile file, Long userId) {
		log.info(">> importContacts({}, {})", file);
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
			List<ContactImportRequestDto> contacts = new CsvToBeanBuilder<ContactImportRequestDto>(bufferedReader)
					.withType(ContactImportRequestDto.class).withSkipLines(1).build().parse();
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
			contacts.forEach(contactdto -> {
				Optional<Contact> contact = contactRepository.findByPhone(contactdto.getPrimaryNumber(),
						 user);
				if (contact.isPresent()) {
					contactRepository.delete(contact.get());
				}
				contact = Optional.ofNullable(new Contact());
				contact.get().setCreatedBy(user);
				contact.get().setModifiedBy(user.getId());
			Contact importContact=saveImportContact(contact.get(), contactdto);
				contactRepository.save(importContact);
			});
		} catch (Exception e) {
			throw new ResourceNotFoundException("Some Problem While Importing Data", "", "");
		}
		return "data has been imported successfully!";
	}

	private Contact updatedContact(Contact contact, ContactRequestDto contactRequestDto) {
		contact.setFirstName(contactRequestDto.getFirstName());
		contact.setLastName(contactRequestDto.getLastName());
		contact.setDob(contactRequestDto.getDob());
		contact.setCity(contactRequestDto.getCity());
		contact.setState(contactRequestDto.getState());
		contact.setCountry(contactRequestDto.getCountry());
		contact.setCourseName(contactRequestDto.getCourseName());
		contact.setCourseDate(contactRequestDto.getCourseDate());
		contact.setFieldOfInterest(contactRequestDto.getFieldOfInterest());
		contact.setPrimaryEmail(contactRequestDto.getPrimaryEmail());
		contact.setPrimaryNumber(contactRequestDto.getPrimaryNumber());
		contact.setSecondaryEmail(contactRequestDto.getSecondaryEmail());
		contact.setSecondaryNumber(contactRequestDto.getSecondaryNumber());
		contact.setCurrentCompany(contactRequestDto.getCurrentCompany());
		contact.setCurrentPosition(contactRequestDto.getCurrentPosition());
		contact.setLinkedInId(contactRequestDto.getLinkedInId());
		contact.setFaceBookId(contactRequestDto.getFaceBookId());
		contact.setInstagramId(contactRequestDto.getInstagramId());
		contact.setCreatedAt(LocalDateTime.now());
		contact.setModifiedAt(LocalDateTime.now());
		return contact;
	}

	private Contact updateContact(Contact contact, UpdateContactRequest contactRequestDto) {
		contact.setDob(contactRequestDto.getDob());
		contact.setCity(contactRequestDto.getCity());
		contact.setState(contactRequestDto.getState());
		contact.setCountry(contactRequestDto.getCountry());
		contact.setCourseName(contactRequestDto.getCourseName());
		contact.setCourseDate(contactRequestDto.getCourseDate());
		contact.setFieldOfInterest(contactRequestDto.getFieldOfInterest());
		contact.setSecondaryEmail(contactRequestDto.getSecondaryEmail());
		contact.setSecondaryNumber(contactRequestDto.getSecondaryNumber());
		contact.setCurrentCompany(contactRequestDto.getCurrentCompany());
		contact.setCurrentPosition(contactRequestDto.getCurrentPosition());
		contact.setLinkedInId(contactRequestDto.getLinkedInId());
		contact.setFaceBookId(contactRequestDto.getFaceBookId());
		contact.setInstagramId(contactRequestDto.getInstagramId());
		contact.setCreatedAt(LocalDateTime.now());
		contact.setModifiedAt(LocalDateTime.now());
		return contact;
	}

	private Contact saveImportContact(Contact contact, ContactImportRequestDto contactRequestDto) {
		contact.setFirstName(contactRequestDto.getFirstName());
		contact.setLastName(contactRequestDto.getLastName());
		contact.setDob(contactRequestDto.getDob());
		contact.setCity(contactRequestDto.getCity());
		contact.setState(contactRequestDto.getState());
		contact.setCountry(contactRequestDto.getCountry());
		contact.setCourseName(contactRequestDto.getCourseName());
		contact.setCourseDate(contactRequestDto.getCourseDate());
		contact.setFieldOfInterest(contactRequestDto.getFieldOfInterest());
		contact.setPrimaryEmail(contactRequestDto.getPrimaryEmail());
		contact.setPrimaryNumber(contactRequestDto.getPrimaryNumber());
		contact.setSecondaryEmail(contactRequestDto.getSecondaryEmail());
		contact.setSecondaryNumber(contactRequestDto.getSecondaryNumber());
		contact.setCurrentCompany(contactRequestDto.getCurrentCompany());
		contact.setCurrentPosition(contactRequestDto.getCurrentPosition());
		contact.setLinkedInId(contactRequestDto.getLinkedInId());
		contact.setFaceBookId(contactRequestDto.getFaceBookId());
		contact.setInstagramId(contactRequestDto.getInstagramId());
		contact.setCreatedAt(LocalDateTime.now());
		contact.setModifiedAt(LocalDateTime.now());
		return contact;
	}

}
