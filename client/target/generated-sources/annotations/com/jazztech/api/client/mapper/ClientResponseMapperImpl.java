package com.jazztech.api.client.mapper;

import com.jazztech.api.client.controller.response.ClientResponse;
import com.jazztech.api.client.repository.entity.AddressEntity;
import com.jazztech.api.client.repository.entity.ClientEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-11T14:17:33-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class ClientResponseMapperImpl implements ClientResponseMapper {

    @Override
    public ClientResponse from(ClientEntity client) {
        if ( client == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        String cpf = null;
        LocalDate dateOfBirth = null;
        ClientResponse.AddressResponse address = null;

        id = client.getId();
        name = client.getName();
        cpf = client.getCpf();
        dateOfBirth = client.getDateOfBirth();
        address = from( client.getAddress() );

        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        ClientResponse clientResponse = new ClientResponse( id, name, cpf, dateOfBirth, address, createdAt, updatedAt );

        return clientResponse;
    }

    @Override
    public ClientResponse.AddressResponse from(AddressEntity address) {
        if ( address == null ) {
            return null;
        }

        UUID id = null;
        String street = null;
        String neighborhood = null;
        String state = null;
        Integer numberOfHouse = null;
        String complement = null;
        String cep = null;

        id = address.getId();
        street = address.getStreet();
        neighborhood = address.getNeighborhood();
        state = address.getState();
        numberOfHouse = address.getNumberOfHouse();
        complement = address.getComplement();
        cep = address.getCep();

        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        ClientResponse.AddressResponse addressResponse = new ClientResponse.AddressResponse( id, street, neighborhood, state, numberOfHouse, complement, cep, createdAt, updatedAt );

        return addressResponse;
    }
}
