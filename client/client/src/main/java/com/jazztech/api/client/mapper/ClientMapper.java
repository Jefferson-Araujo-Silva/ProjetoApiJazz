package com.jazztech.api.client.mapper;

import com.jazztech.api.client.controller.request.ClientRequest;
import com.jazztech.api.client.model.Address;
import com.jazztech.api.client.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client from(ClientRequest clientRequest);
    @Mapping(target = "street", ignore = true)
    @Mapping(target = "neighborhood", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "uf", ignore = true)
    Address from(ClientRequest.AddressRequest addressRequest);
}
