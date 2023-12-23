package com.minton.userservice.repository;


import com.minton.userservice.constants.AuthProvider;
import com.minton.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    
    @Query("select u from User u where u.email=:email and u.provider = :provider")
    Optional<User> findByEmailAndProvider(String email, AuthProvider provider);

    List<User> findAllByIsActive(Boolean isActive);
    User findByIdAndIsActive(Long id,boolean isActive);

    User findByEmailAndIsActive(String email,boolean isActive);
    Boolean existsByEmail(String email);

}
