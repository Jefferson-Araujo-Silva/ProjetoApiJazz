package com.jazztech.api.client.mapper;

import com.jazztech.api.client.model.Address;
import com.jazztech.api.client.model.Client;
import com.jazztech.api.client.repository.entity.AddressEntity;
import com.jazztech.api.client.repository.entity.ClientEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ClientEntityMapper {
    ClientEntity from(Client client);

    AddressEntity from(Address address);
}
