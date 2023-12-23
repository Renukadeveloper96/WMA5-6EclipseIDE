package com.minton.userservice.repository;

import com.minton.userservice.entities.Contact;
import com.minton.userservice.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

	@Query("select c from Contact c where c.id=:id and c.isActive = true")
	Optional<Contact> findActiveById(Long id);

	@Query("SELECT c from Contact c where c.isActive = true and c.id in :contactIds")
	Set<Contact> findAllActiveByContacts(List<Long> contactIds);

	@Query("select c from Contact c where c.id=:contactId and  c.createdBy=:user and c.isActive = true")
	Optional<Contact> findActiveByIdAndUserId(Long contactId, User user);

	@Query("select c from Contact c where c.primaryNumber=:phone and c.createdBy=:user and c.isActive = true")
	Optional<Contact> findByPhone(Long phone, User user);
	
	@Query("select c from Contact c where c.primaryNumber=:phone and c.primaryEmail=:email and c.createdBy=:user and c.isActive = true")
	List<Contact> findAllByPhoneAndEmail(Long phone, String email, User user);

	@Query("select c from Contact c where c.id=:contactId and c.createdBy.id=:userId and c.isActive = true")
	Optional<Contact> findByIdAndUserId(Long contactId, Long userId);

	@Query("SELECT c FROM Contact c WHERE c.createdBy = :user and  c.isActive = true order by c.courseDate DESC")
	Page<Contact> findAllAndUser(Pageable pageable, @Param("user") User user);

}
