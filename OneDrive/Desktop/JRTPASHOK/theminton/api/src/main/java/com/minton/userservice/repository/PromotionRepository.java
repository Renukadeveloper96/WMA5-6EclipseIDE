package com.minton.userservice.repository;

import com.minton.userservice.entities.Promotion;
import com.minton.userservice.entities.Promotion;
import com.minton.userservice.entities.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

	@Query("SELECT p from Promotion p where p.isActive = true")
	List<Promotion> findActiveAll();

	@Query("select p from Promotion p where p.engagement.id=:engagementId and p.isActive = true")
	List<Promotion> findAllByEngagementId(Long engagementId);

	@Query("select p from Promotion p where p.id=:promotionId and p.engagement.id=:engagementId")
	Optional<Promotion> findByIdAndEngagementId(Long promotionId, Long engagementId);

}
