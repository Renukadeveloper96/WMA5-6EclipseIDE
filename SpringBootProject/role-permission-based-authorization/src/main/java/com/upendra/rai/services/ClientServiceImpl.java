package com.upendra.rai.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upendra.rai.dtos.AnnouncementTopicRequestDto;
import com.upendra.rai.dtos.AnnouncementTopicResponseDto;
import com.upendra.rai.dtos.ClientRequestDto;
import com.upendra.rai.dtos.ClientResponseDto;
import com.upendra.rai.entities.AnnouncementTopic;
import com.upendra.rai.entities.Client;
import com.upendra.rai.exceptions.ResourceNotFoundException;
import com.upendra.rai.repositories.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ModelMapper modelMapper;
  
  @Override
  @Transactional(readOnly = true)
  public List<ClientResponseDto> getClients() {
    log.info(">> getClients({}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {})");
    List<Client>clients=clientRepository.findAll();
    return clients.stream().map(client->modelMapper.map(client,ClientResponseDto.class)).collect(Collectors.toList());
  }
  @Override
  @Transactional(readOnly = true)
  public ClientResponseDto getClientByIdOrAlias(String clientId,String clientIdOrAlias) {
    log.info(">> getClientByIdOrAlias({}, {})", clientIdOrAlias);
    Client client = clientRepository.findActiveByUuidOrAlias(clientIdOrAlias).get();
    //    .orElseThrow(() -> new ResourceNotFoundException("client not found"));
    return modelMapper.map(client, ClientResponseDto.class);
  }
  @Override
  @Transactional
  public ClientResponseDto createClient(ClientRequestDto clientRequestDto) {
    log.info(">> createClient({}, {})", clientRequestDto); 
    Client client=modelMapper.map(clientRequestDto, Client.class);
    client.setCode("renuka");
    client.setJoinedTime(LocalDateTime.now());
    client.setUpdatedTime(LocalDateTime.now());
    Client savedClient=clientRepository.save(client);
    return modelMapper.map(savedClient, ClientResponseDto.class);
  }

@Override
public ClientResponseDto getClientById(String clientId1) {
	Client client=clientRepository.findByUuid(clientId1).get();
	return modelMapper.map(client, ClientResponseDto.class);
}
//@Override
//@Transactional
//public AnnouncementTopicResponseDto getUpdateAnnouncementTopic(String clientId,Long id,
//		AnnouncementTopicRequestDto announcementTopicRequestDto) {
//	Client client=clientRepository.findById(id).get();
//	 AnnouncementTopic existingAnnouncementTopic = announcementTopicRepository.findByIdAndClient(id, client)
//                .orElseThrow(() -> new ResourceNotFoundException("AnnouncementTopic not found with id " + id));
//
//	 modelMapper.map(announcementTopicRequestDto, existingAnnouncementTopic);
//        
//        return modelMapper.map(existingAnnouncementTopic, AnnouncementTopicResponseDto.class);
//}
//@PutMapping("/{id}")
//public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) {
//    return departmentService.findById(id)
//            .map(existingDepartment -> {
//                departmentDTO.setId(existingDepartment.getId());
//                return ResponseEntity.ok(departmentService.save(departmentDTO));
//            })
//            .orElse(ResponseEntity.notFound().build());
//}
//@Override
//public ClientResponseDto updateClient(Long id, ClientRequestDto clientRequestDto) {
//	Client client=clientRepository.findById(id).get();
//	client.setCity(clientRequestDto.getCity());
//	client.setName(clientRequestDto.getName());
//	clientRepository.save(client);
//	
//	ClientResponseDto dto=new ClientResponseDto();
//	dto.setCity(client.getCity());
//	dto.setName(client.getName());
//	return dto;
//}
@Override
public Client updateClient(Client clientRequestDto) {
	 Client existingClient = clientRepository.findById(clientRequestDto.getId())
             .orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + clientRequestDto.getId()));
     modelMapper.map(clientRequestDto, existingClient);
     return clientRepository.save(existingClient);
}
//@Override
//public Client updateClient(ClientRequestDto clientRequestDto) {
//	// TODO Auto-generated method stub
//	return null;
//}


}
