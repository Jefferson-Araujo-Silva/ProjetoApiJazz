package com.jazztech.apicadastro.infrastructure.apiClients.dto;

public record ClientDto(String logradouro, String bairro, String uf,
                        String localidade, String cep) {
}

