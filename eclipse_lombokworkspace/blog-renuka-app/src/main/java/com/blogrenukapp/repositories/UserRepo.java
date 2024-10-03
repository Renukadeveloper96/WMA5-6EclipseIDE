package com.blogrenukapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogrenukapp.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
