package com.upendra.rai.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upendra.rai.dtos.AnnouncementTopicRequestDto;
import com.upendra.rai.dtos.AnnouncementTopicResponseDto;
import com.upendra.rai.entities.AnnouncementTopic;
import com.upendra.rai.services.AnnouncementTopicService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/announcementTopics")
public class AnnouncementTopicController {

  @Autowired
  private AnnouncementTopicService announcementTopicService;
  
  @Autowired
  private ModelMapper modelMapper;
  
  @PostMapping
  public AnnouncementTopicResponseDto createDepartment(@RequestHeader String clientId,@RequestBody AnnouncementTopicRequestDto announcementTopicRequestDto) {
      return announcementTopicService.createAnnouncementTopic(clientId,announcementTopicRequestDto);
  }
	
	@GetMapping
	 public List<AnnouncementTopicResponseDto> getAllAnnouncementTopic(@RequestHeader String clientId){
		return announcementTopicService.getAllAnnouncementTopic(clientId);
	}
//	
	@GetMapping(value="/{id}")
	public AnnouncementTopicResponseDto getAnnouncementTopicById(@RequestHeader String clientId,@PathVariable("id") Long id) {
		return announcementTopicService.getByIdAnnouncementTopic(clientId,id);
	}

	
	@PutMapping("/{id}")
    public AnnouncementTopicResponseDto updateAnnouncementTopic(@PathVariable Long id, @RequestBody AnnouncementTopicRequestDto announcementTopicRequestDto) {
		announcementTopicRequestDto.setId(id);
        AnnouncementTopic updatedAnnouncementTopic = announcementTopicService.updateAnnouncementTopic(announcementTopicRequestDto);
        return modelMapper.map(updatedAnnouncementTopic, AnnouncementTopicResponseDto.class);
    }
	
	@DeleteMapping("/{id}")
	public String deleteAnnouncment(@PathVariable("id") Long id) {
		return announcementTopicService.deleteAnnouncementTopicService(id);
	}
//	
//	@DeleteMapping(value="/{id}")
//	public String deletebyid(@PathVariable("id")Long id) {
//		return departmentService.delete(id);
//	}
}
