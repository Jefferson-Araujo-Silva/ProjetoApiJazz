package com.jazztech.apicadastro.presentation.controller;

import com.jazztech.apicadastro.applicationservice.clientsService.CreateClients;
import com.jazztech.apicadastro.applicationservice.clientsService.SearchClients;
import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import com.jazztech.apicadastro.infrastructure.apiClients.ViaCepApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ClientsController {
    private final CreateClients createClients;
    private final SearchClients searchClients;
    @GetMapping("/retornarClients")
    public List<Clients> hello(){
        return searchClients.listAll();
    }
    @PostMapping("/cadastrarClients")
    public ResponseEntity<Clients> cadastrarCliente(@RequestBody Clients clients){
        return new ResponseEntity<>(createClients.save(clients), HttpStatus.CREATED);
    }
}
