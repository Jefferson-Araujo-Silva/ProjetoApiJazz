package com.jazztech.apicadastro.applicationservice.domain.entity;

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

    public Adress(String publicPlace, String neighborhood, String complement, String zipCode, String city, String state) {
        this.publicPlace = publicPlace;
        this.neighborhood = neighborhood;
        this.complement = complement;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }

}
