package com.jazztech.apicadastro.infrastructure.apiClients.dto;

import com.jazztech.apicadastro.applicationservice.domain.entity.Adress;
import com.jazztech.apicadastro.infrastructure.apiClients.ViaCepApiClient;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientDto {

    private ViaCepApiClient viaCepApiClient;

    public ClientDto() {
        this.viaCepApiClient = new ViaCepApiClient();
    }

    public Adress insertAdress(String complement, String cep){
        return viaCepApiClient.createAdress(complement, cep);
    }
}
