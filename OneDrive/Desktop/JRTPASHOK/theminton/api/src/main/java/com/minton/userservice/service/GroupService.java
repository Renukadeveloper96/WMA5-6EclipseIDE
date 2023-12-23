package com.minton.userservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import java.time.LocalDateTime;

import org.apache.commons.collections4.CollectionUtils;
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
import com.minton.userservice.entities.User;
import com.minton.userservice.exception.ResourceNotFoundException;
import com.minton.userservice.payload.GroupContactRequestDto;
import com.minton.userservice.payload.GroupRequestDto;
import com.minton.userservice.payload.GroupResponse;
import com.minton.userservice.payload.PagedResponseDto;
import com.minton.userservice.repository.ContactRepository;
import com.minton.userservice.repository.GroupContactRepository;
import com.minton.userservice.repository.GroupRepository;
import com.minton.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GroupContactRepository groupContactRepository;

	@Transactional
	public String createGroupContacts(GroupContactRequestDto groupRequestDto, Long userId) {
		log.info(">> createGroupContacts({}, {})", groupRequestDto);
		Optional<Group> group = groupRepository.findByGroupName(groupRequestDto.getGroupName());
//		User user = userRepository.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		if (group.isEmpty() && groupRequestDto.getValue() == 0) {
			group = Optional.ofNullable(new Group());
			group.get().setGroupName(groupRequestDto.getGroupName());
			group.get().setCreatedOn(LocalDateTime.now());
			group.get().setIsActive(Boolean.TRUE);
			group.get().setModifiedAt(LocalDateTime.now());
			group.get().setCreatedBy(userId);
			group.get().setModifiedBy(userId);
			groupRepository.save(group.get());
			try {
				groupRepository.save(group.get());
			} catch (DataIntegrityViolationException e) {
				throw new ResourceNotFoundException("Group Name is Already Exist", "", "");
			}
		}
		if (groupRequestDto.getValue() != 0) {
			group = groupRepository.findById(groupRequestDto.getValue());
		}
		final Group newGroup = group.get();

		groupRequestDto.getContactIds().forEach(contactId -> {
			GroupContacts groupContact = new GroupContacts();
			Contact contact = contactRepository.findByIdAndUserId(contactId, userId)
					.orElseThrow(() -> new ResourceNotFoundException("Contact", "", ""));
			List<GroupContacts> gp = groupContactRepository.findByContactAndGroup(contact, newGroup);
			if(CollectionUtils.isEmpty(gp)) {
			groupContact.setContact(contact);
			groupContact.setGroup(newGroup);
			groupContactRepository.save(groupContact);
			}
		});
		return "data has been saved successfully!";
	}

	public List<GroupContacts> getGroupContacts(Long userId) {
		log.info(">> getGroupContacts({})", userId);
		return groupContactRepository.findAll();
	}

	public List<Group> getGroups(Long id) {
		return groupRepository.findAll();
	}

}

//List<Contact> uniqueContact = contactRepository.findAllByPhoneAndEmail(contact.getPrimaryNumber(),
//contact.getPrimaryEmail(), user);
//groupContact.setContact(uniqueContact.get(0));
