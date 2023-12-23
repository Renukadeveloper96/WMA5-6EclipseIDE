package com.minton.userservice.repository;

import com.minton.userservice.entities.Group;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

	@Query("select g from Group g where g.id=:id and g.isActive = true")
	Optional<Group> findActiveById(Long id);

	Optional<Group> findByGroupName(String name);

}
