package com.minton.userservice.repository;

import com.minton.userservice.entities.Contact;
import com.minton.userservice.entities.Group;
import com.minton.userservice.entities.GroupContacts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupContactRepository extends JpaRepository<GroupContacts, Long> {

	@Query("SELECT g from GroupContacts g where g.contact.createdBy.id =:userId")
	List<GroupContacts> findAllByUser(Long userId);
	
	@Query("SELECT g from GroupContacts g where g.contact.id =:Id")
	List<GroupContacts> findByContact(Long Id);

	//@Query("select c from Contact c where c.primaryNumber=:phone and c.primaryEmail=:email and c.createdBy=:user and c.isActive = true")
	List<GroupContacts> findByContactAndGroup(Contact contact, Group group);


}
