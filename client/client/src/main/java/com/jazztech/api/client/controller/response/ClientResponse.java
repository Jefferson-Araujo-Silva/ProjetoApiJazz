package com.jazztech.api.client.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientResponse(
        UUID id,
        String name,
        String cpf,
        LocalDate dateOfBirth,
        AddressResponse address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record AddressResponse(
            UUID id,
            String street,
            String neighborhood,
            String state,
            Integer numberOfHouse,
            String complement,
            String cep,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }
}
