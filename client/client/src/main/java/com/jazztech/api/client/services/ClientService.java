package com.jazztech.api.client.services;

import com.jazztech.api.client.apiclient.ViaCepApiClient;
import com.jazztech.api.client.apiclient.addressdto.AddressViaCep;
import com.jazztech.api.client.controller.request.ClientRequest;
import com.jazztech.api.client.controller.response.ClientResponse;
import com.jazztech.api.client.exception.AddressNotFoundException;
import com.jazztech.api.client.exception.CPFAlreadyExistException;
import com.jazztech.api.client.exception.ClientNotFoundException;
import com.jazztech.api.client.mapper.ClientEntityMapper;
import com.jazztech.api.client.mapper.ClientMapper;
import com.jazztech.api.client.mapper.ClientResponseMapper;
import com.jazztech.api.client.model.Client;
import com.jazztech.api.client.repository.ClientRepository;
import com.jazztech.api.client.repository.entity.ClientEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
        try {
            clientSaved = clientRepository.save(clientEntity);
        } catch (DataIntegrityViolationException e) {
            throw new CPFAlreadyExistException("Cpf %s already exists in database".formatted(clientEntity.getCpf()));
        }
        return clientSaved;
    }

    public AddressViaCep getAddressViaCep(String cep) {
        final AddressViaCep addressViaCep = viaCepApiClient.getAddress(cep);
        if (addressViaCep.cep() == null) {
            throw new AddressNotFoundException("Address not exist to cep %s".formatted(cep));
        }
        return addressViaCep;
    }

    public ClientResponse getClientBy(UUID id) {
        ClientEntity clientEntity;
        final Optional<ClientEntity> clientEntityOptional;
        try {
            clientEntityOptional = clientRepository.findById(id);
        }catch (ClientNotFoundException e){
            throw new ClientNotFoundException("Client not found by id %s".formatted(id));
        }
        clientEntity = clientEntityOptional.get();
        return clientResponseMapper.from(clientEntity);
    }

    public List<ClientResponse> getClientBy(String cpf) {
        final List<ClientEntity> clients;
        if(cpf != null){
            clients = clientRepository.findByCpf(cpf);
        }
        else {
            clients = clientRepository.findAll();
        }
        if (clients.isEmpty()) {
           ClientNotFoundException clientNotFoundException = new ClientNotFoundException("Client no found by cpf %s".formatted(cpf));
            clientNotFoundException.printStackTrace();
            throw clientNotFoundException;
        }
        return clients.stream().map(clientResponseMapper::from).collect(Collectors.toList());
    }

    public List<ClientEntity> getClients() {
        return clientRepository.findAll();

    }

}
