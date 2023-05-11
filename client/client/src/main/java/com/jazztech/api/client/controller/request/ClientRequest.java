package com.jazztech.api.client.controller.request;

import lombok.Builder;

import java.time.LocalDate;

public record ClientRequest(
    String name,
    String cpf,
    LocalDate dateOfBirth,
    AddressRequest address
) {
    @Builder
    public ClientRequest {
    }

    public record AddressRequest(Integer numberOfHouse, String complement, String cep) {
        @Builder
        public AddressRequest {
        }
    }
}
