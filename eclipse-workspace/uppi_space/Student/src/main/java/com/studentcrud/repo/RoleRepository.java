package com.studentcrud.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.studentcrud.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}
