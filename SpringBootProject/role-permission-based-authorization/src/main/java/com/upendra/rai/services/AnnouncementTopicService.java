package com.upendra.rai.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestHeader;

import com.upendra.rai.dtos.AnnouncementTopicRequestDto;
import com.upendra.rai.dtos.AnnouncementTopicResponseDto;
import com.upendra.rai.entities.AnnouncementTopic;

public interface AnnouncementTopicService {

  public AnnouncementTopicResponseDto createAnnouncementTopic(String clientId,
      AnnouncementTopicRequestDto announcementTopicRequestDto);
  
  public List<AnnouncementTopicResponseDto>getAllAnnouncementTopic(String clientId);
  
  public AnnouncementTopicResponseDto getByIdAnnouncementTopic(String clientId,Long id);
  
  AnnouncementTopic updateAnnouncementTopic(AnnouncementTopicRequestDto announcementTopicRequestDto);
  
  public String deleteAnnouncementTopicService(Long id);
  

}
