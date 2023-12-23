package com.minton.userservice.repository;

import com.minton.userservice.entities.EngagementType;
import com.minton.userservice.entities.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EngagementTypeRepository extends JpaRepository<EngagementType, Long> {

	@Query("SELECT et from EngagementType et where et.createdBy.id =:userId and et.isActive = true")
	List<EngagementType> findAllByUser(Long userId);

	@Query("select et from EngagementType et where et.id=:engagementTypeId and et.createdBy.id=:userId and et.isActive = true")
	Optional<EngagementType> findByIdAndUserId(Long engagementTypeId, Long userId);

	@Query("select et from EngagementType et where et.id=:engagementTypeId and  et.createdBy=:user and et.isActive = true")
	Optional<EngagementType> findActiveByIdAndUser(Long engagementTypeId, User user);

	@Query("select et from EngagementType et where et.id=:engagementTypeId and et.isActive = true")
	Optional<EngagementType> findActiveById(Long engagementTypeId);

}
