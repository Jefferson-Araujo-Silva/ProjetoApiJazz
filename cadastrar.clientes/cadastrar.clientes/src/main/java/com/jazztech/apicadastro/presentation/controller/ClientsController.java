package com.jazztech.apicadastro.presentation.controller;

import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import com.jazztech.apicadastro.infrastructure.apiClients.ViaCepApiClient;
import org.springframework.web.bind.annotation.*;



@RestController
public class ClientsController {

    @GetMapping("/cadastrarClients")
    public String hello(){
        return "Olá mundo";
    }
    @PostMapping("/cadastrarClients")
    public void cadastrarCliente(@RequestBody Clients clients){
        System.out.println(clients.toString());

    }
}
