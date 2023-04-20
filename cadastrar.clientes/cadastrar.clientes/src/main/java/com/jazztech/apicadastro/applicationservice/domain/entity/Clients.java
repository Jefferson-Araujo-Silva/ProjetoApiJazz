package com.jazztech.apicadastro.applicationservice.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.br.CPF;


import java.time.LocalDate;

@Entity
@Table(name = "client")
public class Clients  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;

    @NotBlank @NotNull
    private String name;

    @CPF @NotNull
    private String cpf;

    @PastOrPresent @NotNull
    private LocalDate dateOfBirth;
    @Transient @NotNull
    private String zipCode;
    @Transient @NotNull
    private Integer houseNumber;
    @Transient
    private String complement;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adress_id", referencedColumnName = "idAdress")
    private Adress adress;

    public Clients(){}

    public Clients(String name, String cpf, LocalDate dateOfBirth, String zipCode, Integer houseNumber, String complement) {
        this.name = name;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.zipCode = zipCode;
        this.houseNumber = houseNumber;
        if((complement == null) || complement.equals("")){
            this.complement = null;
        }else {this.complement = complement;}
    }

    public String getName() {
        return name;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public long getId() {
        return idClient;
    }
    @Transient
    public String getZipCode() {
        return zipCode;
    }

    @Transient
    public Integer getHouseNumber() {
        return houseNumber;
    }
    @Transient
    public String getComplement() {
        return complement;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
