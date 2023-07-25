package com.itso.occupancy.occupancy.service.client;

import com.itso.occupancy.occupancy.config.errorhandler.customexception.ElementNotFoundException;
import com.itso.occupancy.occupancy.dto.mapper.ClientMapper;
import com.itso.occupancy.occupancy.dto.mapper.ProjectMapper;
import com.itso.occupancy.occupancy.dto.mapper.UserMapper;
import com.itso.occupancy.occupancy.dto.model.client.ClientCreateDto;
import com.itso.occupancy.occupancy.dto.model.client.ClientDto;
import com.itso.occupancy.occupancy.dto.model.client.ClientRechercheDto;
import com.itso.occupancy.occupancy.dto.model.project.ProjectDto;
import com.itso.occupancy.occupancy.dto.model.user.UserDto;
import com.itso.occupancy.occupancy.dto.model.user.UserRechercheDto;
import com.itso.occupancy.occupancy.model.Client;
import com.itso.occupancy.occupancy.model.Project;
import com.itso.occupancy.occupancy.repository.ClientRepository;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    /**
     * @param id
     * @return
     */
    @Override
    public ClientDto getClient(Long id) {
        return ClientMapper.toClientDto(findClientIfExists(id));
    }

    /**
     * @return
     */
    @Override
    public List<ClientDto> getAllClient() {
        return clientRepository.SupprimerIsFalseOrderByName().stream().map(ClientMapper::toClientDto).toList();
    }


    /**
     * @param clientDto
     * @return
     */
    @Override
    public ClientDto updateClient(ClientDto clientDto) {

        Client clientModel = findClientIfExists(clientDto.id());
        clientModel.setName(clientDto.name());

        clientRepository.save(clientModel);
        return ClientMapper.toClientDto(clientModel);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<Object> deleteClient(Long id) {
        Client client = findClientIfExists(id);
        client.setSupprimer(Boolean.TRUE);
        clientRepository.save(client);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ClientDto> postClient(ClientDto client) {
        ClientDto clientDto = ClientMapper.toClientDto(findClientIfExists(client.id()));
        return new ResponseEntity<>(clientDto , HttpStatus.OK);
    }

    @Override
    public List<ClientDto> postAllClient() {
        return clientRepository.SupprimerIsFalseOrderByName().stream().map(ClientMapper::toClientDto).toList();
    }

    @Override
    public ResponseEntity<ClientDto> createClient(ClientCreateDto clientCreateDto){
        Client client = ClientMapper.ToClientModel(clientCreateDto);
        Client client1 = clientRepository.save(client);
        ClientDto clientDto = ClientMapper.toClientDto(client1);
        return new ResponseEntity<>(clientDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ClientDto>> rechercheByName(ClientRechercheDto recherche){
        List<ClientDto> AllUserByJob = clientRepository.findByNameContainingIgnoreCaseAndSupprimerIsFalseOrderByName(recherche.getName()).stream().map(ClientMapper::toClientDto).toList();
        return  new ResponseEntity<>(AllUserByJob, HttpStatus.OK);
    }

    private Client findClientIfExists(Long id) {

        return clientRepository.findByIdAndSupprimerIsFalse(id)
                .orElseThrow(() -> new ElementNotFoundException(
                        String.format("Unable to find Client [id = %s]", id)
                ));
    }
}
