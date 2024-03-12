package com.studentcrud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentcrud.entity.Studentt;

@Repository
public interface StudenttRepository extends JpaRepository<Studentt,Long>{

}
