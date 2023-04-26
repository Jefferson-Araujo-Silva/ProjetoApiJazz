package com.jazztech.apicadastro.applicationservice.clientsService;

import com.jazztech.apicadastro.applicationservice.domain.entity.Adress;
import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import com.jazztech.apicadastro.infrastructure.apiClients.ViaCepApiClient;
import com.jazztech.apicadastro.infrastructure.repository.ClientRepository;
import com.jazztech.apicadastro.infrastructure.apiClients.dto.ClientDto;
import com.jazztech.apicadastro.presentation.dto.CreateClientDto;
import com.jazztech.apicadastro.presentation.handler.ClientNotCreatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CreateClients {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ViaCepApiClient viaCepApiClient;

    public Clients save(CreateClientDto createClientDto) throws ClientNotCreatedException {
        if (clientRepository.countByCpfContains(createClientDto.cpf()) != 0) {
            throw new ClientNotCreatedException(String.format("Não foi possível cadastrar, cliente já cadastrado",
                    createClientDto.cpf()));
        }

        ClientDto clientDtoAdress = viaCepApiClient.createAdress(createClientDto.zipCode());

        Adress adress = new Adress(clientDtoAdress.logradouro(), clientDtoAdress.bairro(),
                createClientDto.complement(), createClientDto.zipCode(), clientDtoAdress.localidade(), clientDtoAdress.uf());

        Clients client = new Clients(createClientDto.name(), createClientDto.cpf(), createClientDto.dateOfBirth(), adress);

        clientRepository.save(client);

        return client;
    }
}
