package com.itso.occupancy.occupancy.dto.mapper;


import com.itso.occupancy.occupancy.dto.model.client.*;
import com.itso.occupancy.occupancy.model.Client;
import com.itso.occupancy.occupancy.model.Project;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClientMapper {

    public static ClientDto toClientDto(Client client){
        return new ClientDto(
                client.getId(),
                client.getName()
                );
    }

    public static Client toClientModel(ClientDto clientDto){

        return new Client()
                .setId(clientDto.id())
                .setName(clientDto.name());
    }

    public static Client IdToClient(Long id){
        return new Client()
                .setId(id);
    }

    public static ClientCreateDto toClientCreateDto(Client client){
        return new ClientCreateDto()
                .setName(client.getName());
    }

    public static Client ToClientModel(ClientCreateDto clientCreateDto){
        return new Client()
                .setName(clientCreateDto.getName());
    }
}
