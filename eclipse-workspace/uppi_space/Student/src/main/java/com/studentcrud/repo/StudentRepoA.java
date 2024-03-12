package com.studentcrud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentcrud.entity.StudentA;

@Repository
public interface StudentRepoA extends JpaRepository<StudentA,Integer>{


}
