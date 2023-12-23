package com.minton.userservice.repository;

import com.minton.userservice.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {

    UserType  findByName(String userType);

    List<UserType> findAllByDeleteFlag(boolean b);
}
