package com.upendra.rai.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upendra.rai.dtos.ClientRequestDto;
import com.upendra.rai.dtos.ClientResponseDto;
import com.upendra.rai.entities.Client;
import com.upendra.rai.exceptions.ServiceException;
import com.upendra.rai.services.ClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/client")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @GetMapping("/{clientIdOrAlias}")
  public ResponseEntity<ClientResponseDto> getClientByIdOrAlias(@RequestHeader String clientId,
      @PathVariable String clientIdOrAlias) {
    log.info(">> getClientByIdOrAlias({}, {})", clientId, clientIdOrAlias);
    ClientResponseDto response = clientService.getClientByIdOrAlias( clientId,clientIdOrAlias);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<ClientResponseDto> createClient(@RequestBody ClientRequestDto clientRequestDto) {
    log.info(">> getClientByIdOrAlias({}, {})",clientRequestDto);
    ClientResponseDto response = clientService.createClient(clientRequestDto);
    return ResponseEntity.ok(response);
  }
  
  @GetMapping
  public ResponseEntity<List<ClientResponseDto>> getClients() {
    log.info(">> getClientByIdOrAlias({}, {})");
//   List<ClientResponseDto> response = clientService.getClients();  
//   return ResponseEntity.ok(response);
    return ResponseEntity.ok(clientService.getClients());
  }
  
  @GetMapping("/{clientId}")
  public ResponseEntity<ClientResponseDto> getClientById(@RequestBody  String clientId) {
	  ClientResponseDto response=clientService.getClientById(clientId);
	  return ResponseEntity.ok(response);
  }

}
