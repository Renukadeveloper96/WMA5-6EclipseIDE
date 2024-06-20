package com.upendra.rai.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upendra.rai.dtos.AnnouncementTopicRequestDto;
import com.upendra.rai.dtos.AnnouncementTopicResponseDto;
import com.upendra.rai.entities.AnnouncementTopic;
import com.upendra.rai.entities.Client;
import com.upendra.rai.exceptions.ResourceNotFoundException;
import com.upendra.rai.repositories.AnnouncementTopicRepository;
import com.upendra.rai.repositories.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AnnouncementTopicServiceImpl implements AnnouncementTopicService {

  @Autowired
  private AnnouncementTopicRepository announcementTopicRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
    private ClientService clientService;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	@Transactional
	public AnnouncementTopicResponseDto createAnnouncementTopic(String clientId,
			AnnouncementTopicRequestDto announcementTopicRequestDto) {
		Client client=clientRepository.findByUuid(clientId).get();
		AnnouncementTopic announcementTopic=modelMapper.map(announcementTopicRequestDto, AnnouncementTopic.class);
		announcementTopic.setClient(client);
		AnnouncementTopic savedAnnouncementTopic=announcementTopicRepository.save(announcementTopic);
		AnnouncementTopicResponseDto announcementTopicResponseDto=modelMapper.map(savedAnnouncementTopic,AnnouncementTopicResponseDto.class );
		return announcementTopicResponseDto;
  }
	@Override
	 @Transactional(readOnly = true)
	public List<AnnouncementTopicResponseDto> getAllAnnouncementTopic(String clientId) {
		Client client=clientRepository.findByUuid(clientId).get();
		List<AnnouncementTopic> announcementTopic=announcementTopicRepository.findAllAndClient(client);
		//return announcementTopic.stream().map((announcementTopic)->modelMapper.map(announcementTopic, AnnouncementTopicResponseDto.class));
		return announcementTopic.stream().map((at)->modelMapper.map(at,AnnouncementTopicResponseDto.class)).collect(Collectors.toList());
	}
	@Override
	public AnnouncementTopicResponseDto getByIdAnnouncementTopic(String clientId,Long id) {
		Client client=clientRepository.findByUuid(clientId).get();
		AnnouncementTopic announcementTopic=announcementTopicRepository.findByIdAndClient(id,client).get();
		
		return modelMapper.map(announcementTopic,AnnouncementTopicResponseDto.class);
	}
//	@Transactional
//	@Override
//	public AnnouncementTopicResponseDto updateAnnouncementTopic(
//			AnnouncementTopicRequestDto announcementTopicRequestDto) {
//		 AnnouncementTopic existingAnnouncementTopic = announcementTopicRepository.findByUuid(announcementTopicRequestDto.getAnnouncementTopicId())
//	                .orElseThrow(() -> new ResourceNotFoundException("AnnouncementTopic not found with id " + announcementTopicRequestDto.getAnnouncementTopicId()));
//
//	        // Update the Client
//	        Client updatedClient = clientService.updateClient(id, clientRequestDto)
//	        existingAnnouncementTopic.setClient(updatedClient);
//
//	        // Update other fields
//	        modelMapper.map(announcementTopicRequestDto, existingAnnouncementTopic);
//
//	        return announcementTopicRepository.save(existingAnnouncementTopic);
//	}
	/*
	 * @Transactional
    public AnnouncementTopic updateAnnouncementTopic(AnnouncementTopicDTO announcementTopicDTO) {
        AnnouncementTopic existingAnnouncementTopic = announcementTopicRepository.findById(announcementTopicDTO.getAnnouncementTopicId())
                .orElseThrow(() -> new ResourceNotFoundException("AnnouncementTopic not found with id " + announcementTopicDTO.getAnnouncementTopicId()));

        // Update the Client
        Client updatedClient = clientService.updateClient(announcementTopicDTO.getClient());
        existingAnnouncementTopic.setClient(updatedClient);

        // Update other fields
        modelMapper.map(announcementTopicDTO, existingAnnouncementTopic);

        return announcementTopicRepository.save(existingAnnouncementTopic);
    }
	 */
	@Transactional
	@Override
	public AnnouncementTopic updateAnnouncementTopic(AnnouncementTopicRequestDto announcementTopicRequestDto) {
		AnnouncementTopic existingAnnouncementTopic = announcementTopicRepository.findById(announcementTopicRequestDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("AnnouncementTopic not found with id " + announcementTopicRequestDto.getId()));

        // Update the Client
        Client updatedClient = clientService.updateClient(announcementTopicRequestDto.getClient());
        existingAnnouncementTopic.setClient(updatedClient);

        // Update other fields
        modelMapper.map(announcementTopicRequestDto, existingAnnouncementTopic);

        return announcementTopicRepository.save(existingAnnouncementTopic);
	}
//	@Transactional
//    public AnnouncementTopic updateAnnouncementTopic(AnnouncementTopicDTO announcementTopicDTO) {
//        AnnouncementTopic existingAnnouncementTopic = announcementTopicRepository.findById(announcementTopicDTO.getAnnouncementTopicId())
//                .orElseThrow(() -> new ResourceNotFoundException("AnnouncementTopic not found with id " + announcementTopicDTO.getAnnouncementTopicId()));
//
//        // Update the Client
//        Client updatedClient = clientService.updateClient(announcementTopicDTO.getClient());
//        existingAnnouncementTopic.setClient(updatedClient);
//
//        // Update other fields
//        modelMapper.map(announcementTopicDTO, existingAnnouncementTopic);
//
//        return announcementTopicRepository.save(existingAnnouncementTopic);
//    }
	@Override
	public String deleteAnnouncementTopicService(Long id) {
		AnnouncementTopic announcementTopic=announcementTopicRepository.findById(id).get();
		 if(announcementTopicRepository.save(announcementTopic) != null) {
			 return "deleted";
		 }else {
			 return "already deleted";
		 }
	}
}
