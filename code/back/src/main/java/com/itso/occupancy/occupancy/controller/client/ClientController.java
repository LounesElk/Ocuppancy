package com.itso.occupancy.occupancy.controller.client;

import com.itso.occupancy.occupancy.dto.model.client.*;
import com.itso.occupancy.occupancy.service.client.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    /**
     * @return
     */
    @GetMapping("")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<ClientDto>> getAllClient() { //Récupère des clients par get
        return ResponseEntity.ok(clientService.getAllClient());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<ClientDto> getClient(@PathVariable Long id) {  //Récupère un client par get
        return ResponseEntity.ok(clientService.getClient(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<ClientDto> putClient(@RequestBody ClientDto clientDto) { //Modifie un client
        return ResponseEntity.ok(clientService.updateClient(clientDto));
    }

    @PostMapping("")
    public ResponseEntity<List<ClientDto>> postAllClient() { //Récupère des clients par post
        return ResponseEntity.ok(clientService.postAllClient());
    }

    @PostMapping("/id")
//    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<ClientDto> postClient(@RequestBody ClientDto client) { //Récupère un client par post
        return clientService.postClient(client);
    }
    @DeleteMapping("/{id}")
    //@PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<Object> deleteClient(@PathVariable Long id){ //Supprime un client
        return clientService.deleteClient(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientCreateDto clientCreateDto) { //Crée un client
        return clientService.createClient(clientCreateDto);
    }

    @PostMapping("/recherche")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<ClientDto>> rechercheByName(@RequestBody ClientRechercheDto recherche) { //Recherche d'un client
        return clientService.rechercheByName(recherche);
    }

}
