package com.jazztech.apicadastro.presentation.controller;

import com.jazztech.apicadastro.applicationservice.clientsService.CreateClients;
import com.jazztech.apicadastro.applicationservice.clientsService.SearchClients;
import com.jazztech.apicadastro.applicationservice.domain.entity.Adress;
import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import com.jazztech.apicadastro.presentation.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class ClientsController {
    private final CreateClients createClients;
    private final SearchClients searchClients;
    private final ClientDto clientDto;

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
    public ResponseEntity<Clients> registerClient(@RequestBody Clients clients){
        Adress adress = clientDto.createAdress(clients);
        return new ResponseEntity<>(createClients.save(clients, adress), HttpStatus.CREATED);
    }
}
