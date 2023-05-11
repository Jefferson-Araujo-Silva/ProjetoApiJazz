package com.jazztech.api.client.model;

import lombok.Builder;

public record Address(
        String cep,
        String street,
        String neighborhood,
        String state,
        String uf,
        Integer numberOfHouse,
        String complement
) {
    @Builder(toBuilder = true)
    public Address(String cep, String street, String neighborhood,
                   String state, String uf, Integer numberOfHouse, String complement) {
        this.cep = cep;
        this.street = street;
        this.neighborhood = neighborhood;
        this.state = state;
        this.uf = uf;
        this.numberOfHouse = numberOfHouse;
        this.complement = complement;
    }
}