package com.jazztech.apicadastro.applicationservice.domain.entity;

import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


import java.time.LocalDate;

@Entity
public class Clients  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;
    private String cpf;
    private LocalDate dateOfBirthday;
    private String cep;
    private Integer numberOfResidence;
    private String complement;

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

    public Adress criarEndereco(){
        Cep cep1 = ViaCepClient.findCep(cep);
        return new Adress(cep1.getLogradouro(), cep1.getBairro(),
                complement, cep1.getCep(), cep1.getLocalidade(), cep1.getUf());
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public Integer getNumberOfResidence() {
        return numberOfResidence;
    }

    public String getComplement() {
        return complement;
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
