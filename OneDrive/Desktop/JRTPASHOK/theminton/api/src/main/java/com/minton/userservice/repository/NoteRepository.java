package com.minton.userservice.repository;

import com.minton.userservice.entities.Note;
import com.minton.userservice.entities.Role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

	@Query("SELECT n FROM Note n WHERE n.createdBy.id = :userId ORDER BY n.createdAt DESC")
	List<Note> findAllByCreatedAtDESC(Long userId);

}
