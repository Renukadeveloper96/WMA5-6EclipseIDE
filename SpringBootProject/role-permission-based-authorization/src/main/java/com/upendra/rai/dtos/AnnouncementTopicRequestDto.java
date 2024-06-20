package com.upendra.rai.dtos;
import com.upendra.rai.entities.Client;

import lombok.Data;

@Data
public class AnnouncementTopicRequestDto {

//  @NotBlank(message = "NotBlank.announcementTopicRequestDto.topicName")
//  @Size(max = 100,
//      message = "Size.announcementTopicRequestDto.topicName(::)"
//          + 100)
 
  private Long id;
  private String topicName;
  private Client client;
  private String announcementTopicId;
  private Boolean isPrimary;
  private Long subscriptionCount;
  private Boolean isActive;

}
