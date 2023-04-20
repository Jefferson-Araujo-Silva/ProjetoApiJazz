package com.jazztech.apicadastro.applicationservice.domain.entity;

import com.gtbr.domain.Cep;
import jakarta.persistence.*;
import lombok.experimental.NonFinal;

@Entity
@Table(name = "adress")
public class Adress{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idAdress;
        private String publicPlace;
        private String neighborhood;
        private String complement;
        private String zipCode;
        private String city;
        private String state;

        @Transient
        private Clients clients;

        public Adress(){}
    public Adress(Cep cep, String complement) {
        this.neighborhood = cep.getBairro();
        this.zipCode = cep.getCep();
        this.city = cep.getLocalidade();
        this.state = cep.getUf();
        this.complement = complement;
        this.publicPlace = cep.getLogradouro();
    }
}
