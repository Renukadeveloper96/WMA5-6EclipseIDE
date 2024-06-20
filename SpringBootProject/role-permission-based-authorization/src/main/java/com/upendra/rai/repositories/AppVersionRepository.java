package com.upendra.rai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upendra.rai.entities.AppVersion;

@Repository
public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {


}
