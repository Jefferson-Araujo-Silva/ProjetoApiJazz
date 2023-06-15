package com.jazztech.api.client.mapper;

import com.jazztech.api.client.controller.response.ClientResponse;
import com.jazztech.api.client.repository.entity.AddressEntity;
import com.jazztech.api.client.repository.entity.ClientEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-18T09:57:04-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class ClientResponseMapperImpl implements ClientResponseMapper {

    @Override
    public ClientResponse from(ClientEntity client) {
        if ( client == null ) {
            return null;
        }

        ClientResponse.ClientResponseBuilder clientResponse = ClientResponse.builder();

        clientResponse.id( client.getId() );
        clientResponse.name( client.getName() );
        clientResponse.cpf( client.getCpf() );
        clientResponse.dateOfBirth( client.getDateOfBirth() );
        clientResponse.address( from( client.getAddress() ) );

        return clientResponse.build();
    }

    @Override
    public ClientResponse.AddressResponse from(AddressEntity address) {
        if ( address == null ) {
            return null;
        }

        ClientResponse.AddressResponse.AddressResponseBuilder addressResponse = ClientResponse.AddressResponse.builder();

        addressResponse.id( address.getId() );
        addressResponse.street( address.getStreet() );
        addressResponse.neighborhood( address.getNeighborhood() );
        addressResponse.state( address.getState() );
        addressResponse.numberOfHouse( address.getNumberOfHouse() );
        addressResponse.complement( address.getComplement() );
        addressResponse.cep( address.getCep() );

        return addressResponse.build();
    }
}
