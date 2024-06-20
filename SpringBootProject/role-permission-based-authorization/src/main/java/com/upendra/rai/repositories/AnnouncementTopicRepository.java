package com.upendra.rai.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import com.gt.madinaapps.client.service.api.entities.QAnnouncementTopic;
//import com.querydsl.core.types.dsl.StringExpression;
//import com.querydsl.core.types.dsl.StringPath;
import com.upendra.rai.entities.AnnouncementTopic;
import com.upendra.rai.entities.Client;



@Repository
public interface AnnouncementTopicRepository extends JpaRepository<AnnouncementTopic, Long>{

  Optional<AnnouncementTopic> findByUuid(String uuid);

//  @Query("select at from AnnouncementTopic at where at.uuid=:announcementTopicId and at.isActive= true")
//  Optional<AnnouncementTopic> findActiveByUuid(String announcementTopicId);
//
  @Query("select at from AnnouncementTopic at where at.client = :client")
  List<AnnouncementTopic> findAllAndClient(Client client);
  
  @Query("select at from AnnouncementTopic at where at.id=:announcementTopicId and at.client = :client")
  Optional<AnnouncementTopic> findByIdAndClient(Long announcementTopicId, Client client);

//
//  @Query("select at from AnnouncementTopic at where at.uuid=:announcementTopicId and at.client = :client and at.isActive = true")
//  Optional<AnnouncementTopic> findActiveByUuidAndClient(String announcementTopicId, Client client);
//
//  @Query("select at from AnnouncementTopic at where at.client = :client and at.isPrimary= true and at.isActive = true")
//  Optional<AnnouncementTopic> findActiveAndPrimaryByClient(Client client);


}
