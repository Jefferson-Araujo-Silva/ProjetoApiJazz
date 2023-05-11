package com.jazztech.api.client.apiclient.addressdto;

import lombok.Builder;

public record AddressViaCep(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String uf
) {
    @Builder(toBuilder = true)
    public AddressViaCep(String cep, String logradouro, String complemento, String bairro, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.uf = uf;
    }
}
