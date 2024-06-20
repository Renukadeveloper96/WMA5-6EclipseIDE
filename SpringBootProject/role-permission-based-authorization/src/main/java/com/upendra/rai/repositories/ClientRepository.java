package com.upendra.rai.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.upendra.rai.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	
  @Query("select c from Client c where c.uuid = :clientId and c.isActive = true")
  Optional<Client> findActiveByUuid(String clientId);

  Optional<Client> findByUuid(String uuid);

  @Query("select c from Client c where (c.uuid = :idOrAlias or c.alias = :idOrAlias) and c.isActive = true")
  Optional<Client> findActiveByUuidOrAlias(String idOrAlias);

}
