package com.minton.userservice.repository;

import com.minton.userservice.entities.TemplateType;
import com.minton.userservice.entities.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateTypeRepository extends JpaRepository<TemplateType, Long> {

	@Query("SELECT et from TemplateType et where et.createdBy.id =:userId and et.isActive = true")
	List<TemplateType> findAllByUser(Long userId);

	@Query("select et from TemplateType et where et.id=:engagementTypeId and et.createdBy.id=:userId and et.isActive = true")
	Optional<TemplateType> findByIdAndUserId(Long engagementTypeId, Long userId);

	@Query("select et from TemplateType et where et.id=:engagementTypeId and  et.createdBy=:user and et.isActive = true")
	Optional<TemplateType> findActiveByIdAndUser(Long engagementTypeId, User user);

	@Query("select et from TemplateType et where et.id=:engagementTypeId and et.isActive = true")
	Optional<TemplateType> findActiveById(Long engagementTypeId);

}
