package com.jazztech.api.client.mapper;

import com.jazztech.api.client.controller.request.ClientRequest;
import com.jazztech.api.client.model.Address;
import com.jazztech.api.client.model.Client;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-12T09:38:33-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client from(ClientRequest clientRequest) {
        if ( clientRequest == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.name( clientRequest.name() );
        client.cpf( clientRequest.cpf() );
        client.dateOfBirth( clientRequest.dateOfBirth() );
        client.address( from( clientRequest.address() ) );

        return client.build();
    }

    @Override
    public Address from(ClientRequest.AddressRequest addressRequest) {
        if ( addressRequest == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.cep( addressRequest.cep() );
        address.numberOfHouse( addressRequest.numberOfHouse() );
        address.complement( addressRequest.complement() );

        return address.build();
    }
}
