package com.jazztech.apicadastro.presentation.controller;

import com.jazztech.apicadastro.applicationservice.clientsService.CreateClients;
import com.jazztech.apicadastro.applicationservice.clientsService.DeleteClients;
import com.jazztech.apicadastro.applicationservice.clientsService.SearchClients;
import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import com.jazztech.apicadastro.presentation.dto.CreateClientDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientsController {
    @Autowired
    private final CreateClients createClients;
    @Autowired
    private final SearchClients searchClients;
    @Autowired
    private final DeleteClients deleteClients;

    private final Logger LOOGER = LoggerFactory.getLogger(ClientsController.class);


    @GetMapping("/client")
    public ResponseEntity<List<Clients>> getAllClients(){
        LOOGER.info("Retornando clientes");
        return ResponseEntity.status(200).body(searchClients.listAll());
    }

    @GetMapping("adress/{idAdress}")
    public List<Object[]> getAllClientsAndAdress(@PathVariable Integer idAdress){
        return searchClients.findPessoaAndAdressById(idAdress);
    }
    @GetMapping("/{id}")
    public Optional<Clients> getClientsById(@RequestParam Integer id){
        return searchClients.findById(id);
    }
    @PostMapping
    public ResponseEntity<Clients> registerClient(@Valid @RequestBody CreateClientDto clients){
        return new ResponseEntity<>(createClients.save(clients), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable int id){
        return deleteClients.delete(id);
    }
}
