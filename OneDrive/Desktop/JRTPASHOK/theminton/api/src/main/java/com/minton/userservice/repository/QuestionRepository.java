package com.minton.userservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.minton.userservice.entities.Engagement;
import com.minton.userservice.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query("SELECT q from Question q where q.isActive = true")
	List<Question> findActiveAll();

	@Query("select q from Question q where q.engagement.id=:engagementId and q.isActive = true")
	List<Question> findAllByEngagementId(Long engagementId);

	@Query("select q from Question q where q.id=:questionId and q.engagement.id=:engagementId")
	Optional<Question> findByIdAndEngagementId(Long questionId, Long engagementId);

	@Query("select q from Question q where q.engagement.id=:engagementId and q.question=:question and q.isActive = true")
	Optional<Question> findByIdAndQuestionName(Long engagementId, String question);

}
