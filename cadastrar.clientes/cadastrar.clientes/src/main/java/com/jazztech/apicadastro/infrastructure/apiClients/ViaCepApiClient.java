package com.jazztech.apicadastro.infrastructure.apiClients;

import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;
import com.jazztech.apicadastro.applicationservice.domain.entity.Adress;
import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import org.springframework.stereotype.Component;

@Component
public class ViaCepApiClient {

    public Adress createAdress(String complement, String cep){
            Cep cepUser = ViaCepClient.findCep(cep);

        return new Adress(cepUser,complement);
    }
}
