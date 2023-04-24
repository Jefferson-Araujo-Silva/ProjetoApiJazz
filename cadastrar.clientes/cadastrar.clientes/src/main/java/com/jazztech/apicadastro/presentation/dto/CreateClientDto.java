package com.jazztech.apicadastro.presentation.dto;

import java.time.LocalDate;

public record CreateClientDto(String name, String cpf, LocalDate dateOfBirth, String zipCode, String complement,
                              String houseNumber) {

}
