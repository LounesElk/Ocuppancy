package com.itso.occupancy.occupancy.service.client;

import com.itso.occupancy.occupancy.dto.model.client.*;
import com.itso.occupancy.occupancy.dto.model.user.UserDto;
import com.itso.occupancy.occupancy.dto.model.user.UserRechercheDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {

    ClientDto getClient(Long id);
    ResponseEntity<ClientDto> postClient(ClientDto client);
    List<ClientDto> getAllClient();
    List<ClientDto> postAllClient();
    ClientDto updateClient(ClientDto client);
    ResponseEntity<Object> deleteClient(Long id);
    ResponseEntity<ClientDto> createClient(ClientCreateDto client);
    ResponseEntity<List<ClientDto>> rechercheByName(ClientRechercheDto recherche);
}
