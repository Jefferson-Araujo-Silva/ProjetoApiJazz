package com.jazztech.apicadastro.presentation.dto;

import com.jazztech.apicadastro.applicationservice.domain.entity.Adress;
import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import com.jazztech.apicadastro.infrastructure.apiClients.ViaCepApiClient;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
@SpringBootApplication
@RequiredArgsConstructor @ComponentScan(basePackages = "com.jazztech.apicadastro")
public class ClientDto {
    @Autowired
    private ViaCepApiClient viaCepApiClient;
    public Adress createAdress(Clients clients){
        return viaCepApiClient.createAdress(clients);
    }
}
