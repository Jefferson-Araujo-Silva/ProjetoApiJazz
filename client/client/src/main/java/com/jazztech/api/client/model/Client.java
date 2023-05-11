package com.jazztech.api.client.model;



import com.jazztech.api.client.apiclient.addressdto.AddressViaCep;
import lombok.Builder;

import java.time.LocalDate;

//@NoArgsConstructor(force = true)
public record Client(
        String name,
        String cpf,
        LocalDate dateOfBirth,
        Address address
) {

    @Builder(toBuilder = true)
    public Client(String name, String cpf, LocalDate dateOfBirth, Address address) {
        this.name = name;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
    public Client updateAddressFrom(AddressViaCep addressViaCep) {
        return this.toBuilder()
                .address(Address.builder()
                        .cep(this.address.cep())
                        .state(addressViaCep.uf())
                        .street(addressViaCep.logradouro())
                        .numberOfHouse(this.address.numberOfHouse())
                        .complement(this.address.complement())
                        .neighborhood(addressViaCep.bairro())
                        .build())
                .build();
    }
}
