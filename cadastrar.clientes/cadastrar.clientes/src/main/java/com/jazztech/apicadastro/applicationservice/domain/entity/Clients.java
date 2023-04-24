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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adress_id", referencedColumnName = "idAdress")
    private Adress adress;

    public Clients(){}

    public Clients(String name, String cpf, LocalDate dateOfBirth, Adress adress) {
        this.name = name;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.adress = adress;
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

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
