package com.jazztech.apicadastro.presentation.controller;

import com.jazztech.apicadastro.applicationservice.clientsService.CreateClients;
import com.jazztech.apicadastro.applicationservice.clientsService.SearchClients;
import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import com.jazztech.apicadastro.presentation.dto.CreateClientDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class ClientsController {
    @Autowired
    private final CreateClients createClients;
    @Autowired
    private final SearchClients searchClients;


    @GetMapping("/returnClients")
    public List<Clients> getAllClients(){
        return searchClients.listAll();
    }

    @GetMapping("/returnClientsAndAdress/{idAdress}")
    public List<Object[]> getAllClientsAndAdress(@PathVariable long idAdress){
        return searchClients.findPessoaAndEnderecoById(idAdress);
    }
    @GetMapping("/returnClientsById/{id}")
    public Optional<Clients> getClientsById(@PathVariable Long id){
        return searchClients.findById(id);
    }
    @PostMapping("/registerClients")
    public ResponseEntity<Clients> registerClient(@Valid @RequestBody CreateClientDto clients){
        return new ResponseEntity<>(createClients.save(clients), HttpStatus.CREATED);
    }
}
