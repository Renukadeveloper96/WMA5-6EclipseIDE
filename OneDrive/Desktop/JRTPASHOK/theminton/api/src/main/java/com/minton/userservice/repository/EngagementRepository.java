package com.minton.userservice.repository;

import com.minton.userservice.entities.Engagement;
import com.minton.userservice.entities.EngagementType;
import com.minton.userservice.entities.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EngagementRepository extends JpaRepository<Engagement, Long> {
	
	@Query("SELECT et from Engagement et where et.createdBy.id =:userId and et.isActive = true")
	List<Engagement> findAllByUser(Long userId);

	@Query("select et from Engagement et where et.id=:engagementTypeId and et.createdBy.id=:userId and et.isActive = true")
	Optional<Engagement> findByIdAndUserId(Long engagementTypeId, Long userId);
	
	@Query("select et from Engagement et where et.id=:engagementTypeId and  et.createdBy=:user and et.isActive = true")
	Optional<Engagement> findActiveByIdAndUser(Long engagementTypeId, User user);

	@Query("SELECT et from Engagement et where et.isActive = true")
	List<Engagement> findActiveAll();

}
