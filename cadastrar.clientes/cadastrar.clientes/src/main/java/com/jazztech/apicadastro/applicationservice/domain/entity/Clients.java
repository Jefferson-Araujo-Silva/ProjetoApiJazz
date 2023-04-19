package com.jazztech.apicadastro.applicationservice.domain.entity;

import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idClient;

    @NotBlank @NotNull
    private String nome;

    @CPF @NotNull
    private String cpf;

    @PastOrPresent @NotNull
    private LocalDate dateOfBirthday;
    @Transient @NotNull
    private String cep;
    @Transient @NotNull
    private Integer numberOfResidence;
    @Transient
    private String complement;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adress_id", referencedColumnName = "idAdress")
    private Adress adress;

    public Clients(){}

    public Clients(String nome, String cpf, LocalDate dateOfBirthday, String cep, Integer numberOfResidence, String complement) {
        this.nome = nome;
        this.cpf = cpf;
        this.dateOfBirthday = dateOfBirthday;
        this.cep = cep;
        this.numberOfResidence = numberOfResidence;
        if((complement == null) || complement.equals("")){
            this.complement = null;
        }else {this.complement = complement;}
    }

    public String getNome() {
        return nome;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public long getId() {
        return idClient;
    }
    @Transient
    public String getCep() {
        return cep;
    }

    @Transient
    public Integer getNumberOfResidence() {
        return numberOfResidence;
    }
    @Transient
    public String getComplement() {
        return complement;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dateOfBirthday=" + dateOfBirthday +
                ", cep='" + cep + '\'' +
                ", numberOfResidence=" + numberOfResidence +
                ", complement='" + complement + '\'' +
                '}';
    }
}
