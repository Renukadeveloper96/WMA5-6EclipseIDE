package com.studentcrud.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentcrud.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {

	
}
