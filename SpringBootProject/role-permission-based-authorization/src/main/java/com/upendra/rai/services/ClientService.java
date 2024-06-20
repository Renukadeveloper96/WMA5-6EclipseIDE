package com.upendra.rai.services;


import java.util.List;
import java.util.Optional;

import com.upendra.rai.dtos.ClientRequestDto;
import com.upendra.rai.dtos.ClientResponseDto;
import com.upendra.rai.entities.Client;

public interface ClientService {

  List<ClientResponseDto> getClients();

  ClientResponseDto getClientByIdOrAlias(String clientId,String clientIdOrAlias);

  ClientResponseDto createClient(ClientRequestDto clientRequestDto);
  
  ClientResponseDto getClientById(String clientId1);
  
//  ClientResponseDto updateClient(Long id,ClientRequestDto clientRequestDto);
  //ClientResponseDto updateClient(Long id, ClientRequestDto ClientRequestDto);
//  Client updateClient(ClientRequestDto clientRequestDto) ;

Client updateClient(Client client);
}
