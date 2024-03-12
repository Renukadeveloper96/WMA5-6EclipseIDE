package com.studentcrud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentcrud.entity.Library;

@Repository
public interface LibraryRepo extends JpaRepository<Library,Integer>{

}
