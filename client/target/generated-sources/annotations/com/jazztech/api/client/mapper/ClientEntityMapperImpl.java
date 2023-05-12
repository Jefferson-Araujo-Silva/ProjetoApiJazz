package com.jazztech.api.client.mapper;

import com.jazztech.api.client.model.Address;
import com.jazztech.api.client.model.Client;
import com.jazztech.api.client.repository.entity.AddressEntity;
import com.jazztech.api.client.repository.entity.ClientEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-12T09:38:33-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class ClientEntityMapperImpl implements ClientEntityMapper {

    @Override
    public ClientEntity from(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientEntity.ClientEntityBuilder clientEntity = ClientEntity.builder();

        clientEntity.name( client.name() );
        clientEntity.cpf( client.cpf() );
        clientEntity.dateOfBirth( client.dateOfBirth() );
        clientEntity.address( from( client.address() ) );

        return clientEntity.build();
    }

    @Override
    public AddressEntity from(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressEntity.AddressEntityBuilder addressEntity = AddressEntity.builder();

        addressEntity.street( address.street() );
        addressEntity.neighborhood( address.neighborhood() );
        addressEntity.uf( address.uf() );
        addressEntity.state( address.state() );
        addressEntity.numberOfHouse( address.numberOfHouse() );
        addressEntity.complement( address.complement() );
        addressEntity.cep( address.cep() );

        return addressEntity.build();
    }
}
