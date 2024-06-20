
package com.upendra.rai.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upendra.rai.entities.AppScreen;

@Repository
public interface AppScreenRepository extends JpaRepository<AppScreen, Long> {

  Optional<AppScreen> findByUuid(String uuid);

}