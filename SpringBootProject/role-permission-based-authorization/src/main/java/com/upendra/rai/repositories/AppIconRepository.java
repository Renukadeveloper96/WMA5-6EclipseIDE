package com.upendra.rai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upendra.rai.entities.AppIcon;

@Repository
public interface AppIconRepository extends JpaRepository<AppIcon, Long>{

}