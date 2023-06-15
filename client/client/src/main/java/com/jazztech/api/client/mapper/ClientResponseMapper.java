package com.jazztech.api.client.mapper;

import com.jazztech.api.client.controller.response.ClientResponse;
import com.jazztech.api.client.repository.entity.AddressEntity;
import com.jazztech.api.client.repository.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientResponseMapper {
    ClientResponse from(ClientEntity client);
    ClientResponse.AddressResponse from(AddressEntity address);
}