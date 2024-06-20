package com.upendra.rai.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.upendra.rai.entities.Client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementTopicResponseDto {

  private ClientResponseDto client;
  private String announcementTopicId;
  private String topicName;
  private Boolean isPrimary;
  private Long subscriptionCount;
  private Boolean isActive;
}
