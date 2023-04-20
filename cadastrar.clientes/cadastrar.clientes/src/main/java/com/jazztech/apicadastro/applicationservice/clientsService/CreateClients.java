package com.jazztech.apicadastro.applicationservice.clientsService;

import com.jazztech.apicadastro.applicationservice.domain.entity.Adress;
import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import com.jazztech.apicadastro.infrastructure.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CreateClients {
    private final ClientRepository clientRepository;
    public Clients save(Clients client, Adress adress){
        client.setAdress(adress);
        clientRepository.save(client);
        return client;
    }
}
