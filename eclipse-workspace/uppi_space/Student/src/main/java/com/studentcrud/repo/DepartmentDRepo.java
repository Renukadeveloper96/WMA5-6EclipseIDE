package com.studentcrud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentcrud.entity.DepartmentD;

@Repository
public interface DepartmentDRepo extends JpaRepository<DepartmentD,Integer>{

}
