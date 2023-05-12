package com.jazztech.api.client.services;

import com.jazztech.api.client.apiclient.ViaCepApiClient;
import com.jazztech.api.client.apiclient.addressdto.AddressViaCep;
import com.jazztech.api.client.controller.request.ClientRequest;
import com.jazztech.api.client.controller.response.ClientResponse;
import com.jazztech.api.client.exception.ClientNotFoundException;
import com.jazztech.api.client.mapper.ClientEntityMapper;
import com.jazztech.api.client.mapper.ClientMapper;
import com.jazztech.api.client.mapper.ClientResponseMapper;
import com.jazztech.api.client.model.Client;
import com.jazztech.api.client.repository.ClientRepository;
import com.jazztech.api.client.repository.entity.ClientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ViaCepApiClient viaCepApiClient;
    private final ClientEntityMapper clientEntityMapper;
    private final ClientResponseMapper clientResponseMapper;
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;
    public ClientResponse create(ClientRequest clientRequest) {
        final Client client = clientMapper.from(clientRequest);
        final String cep = client.address().cep();
        final AddressViaCep addressViaCep = getAddressViaCep(cep);
        final Client clientAddressUpdated = client.updateAddressFrom(addressViaCep);
        final ClientEntity clientEntity = clientEntityMapper.from(clientAddressUpdated);
        final ClientEntity clientSaved = saveClient(clientEntity);
        return clientResponseMapper.from(clientSaved);
    }

    private ClientEntity saveClient(ClientEntity clientEntity) {
        final ClientEntity clientSaved;
        clientSaved = clientRepository.save(clientEntity);
        return clientSaved;
    }

    public AddressViaCep getAddressViaCep(String cep) {
        final AddressViaCep addressViaCep = viaCepApiClient.getAddress(cep);
        return addressViaCep;
    }

    public ClientResponse getClientBy(UUID id) {
        ClientEntity clientEntity;
        try {
            final Optional<ClientEntity> clientEntityOptional = clientRepository.findById(id);
            clientEntity = clientEntityOptional.get();
        }catch(ClientNotFoundException e){
            throw new ClientNotFoundException("Client not found by id %s".formatted(id));
        }
        return clientResponseMapper.from(clientEntity);
    }

    public ClientResponse getClientBy(String cpf) {
        final ClientEntity clientEntity = clientRepository.findByCpf(cpf);
        return clientResponseMapper.from(clientEntity);
    }
    public List<ClientEntity> getClients() {
        return clientRepository.findAll();

    }

}
