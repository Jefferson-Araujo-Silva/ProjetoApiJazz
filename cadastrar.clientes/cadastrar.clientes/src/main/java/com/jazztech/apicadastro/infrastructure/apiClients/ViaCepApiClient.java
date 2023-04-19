package com.jazztech.apicadastro.infrastructure.apiClients;

import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;
import com.jazztech.apicadastro.applicationservice.domain.entity.Adress;
import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import org.springframework.stereotype.Component;

@Component
public class ViaCepApiClient {

    public Adress createAdress(Clients client){
        Cep cep = ViaCepClient.findCep(client.getCep());

        return new Adress(cep,client);
    }
}
