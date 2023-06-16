package com.jazztech.api.client.controller;

import com.jazztech.api.client.controller.request.ClientRequest;
import com.jazztech.api.client.controller.response.ClientResponse;
import com.jazztech.api.client.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1.0/clients")
public class ClientController {
    @Autowired
    private final ClientService clientService;
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ClientResponse postClient(@RequestBody ClientRequest clientRequest){
        return clientService.create(clientRequest);
    }
    @GetMapping
    public List<ClientResponse> getClientBy(@RequestParam(value = "cpf" , required = false) String cpf){
        return clientService.getClientBy(cpf);
    }
    @GetMapping(path = "/{id}")
    public ClientResponse getClient(@PathVariable(value = "id") String id){
        UUID idResponse = UUID.fromString(id);
        return clientService.getClientBy(idResponse);
    }
}
