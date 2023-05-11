package com.jazztech.api.client.controller;

import com.jazztech.api.client.controller.request.ClientRequest;
import com.jazztech.api.client.controller.response.ClientResponse;
import com.jazztech.api.client.repository.entity.ClientEntity;
import com.jazztech.api.client.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1.0/clients")
public class ClientController {
    @Autowired
    private final ClientService clientService;
    @PostMapping
    public ClientResponse createClient(@RequestBody ClientRequest clientRequest){
        return clientService.create(clientRequest);
    }
    @GetMapping
    public List<ClientEntity> searchClients(){
        return clientService.getClients();
    }
    @GetMapping(path = "/{cpf}")
    public ClientResponse searchClientsBy(@RequestParam String cpf){
        return clientService.getClientBy(cpf);
    }
}
