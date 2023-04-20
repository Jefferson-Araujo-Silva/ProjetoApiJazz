package com.jazztech.apicadastro.presentation.dto;

import com.jazztech.apicadastro.applicationservice.domain.entity.Adress;
import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
 @ComponentScan(basePackages = "com.jazztech.apicadastro")
public class ClientDto {

    private com.jazztech.apicadastro.infrastructure.apiClients.dto.ClientDto clientDto;
@Autowired
    public ClientDto() {
        this.clientDto = new com.jazztech.apicadastro.infrastructure.apiClients.dto.ClientDto();
    }


    public Adress createAdress(Clients client){
        return clientDto.insertAdress(client.getComplement(), client.getZipCode());
    }

}
