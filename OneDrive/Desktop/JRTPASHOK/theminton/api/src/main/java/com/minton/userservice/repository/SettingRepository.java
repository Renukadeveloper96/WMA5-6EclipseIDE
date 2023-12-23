package com.minton.userservice.repository;

import com.minton.userservice.entities.Setting;
import com.minton.userservice.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
	
	@Query("SELECT s FROM Setting s WHERE s.createdBy = :user")
	Optional<Setting> findByUser(User user);

}
