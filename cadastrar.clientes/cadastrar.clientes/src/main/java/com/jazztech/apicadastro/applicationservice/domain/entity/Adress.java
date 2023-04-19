package com.jazztech.apicadastro.applicationservice.domain.entity;

import jakarta.persistence.*;

@Entity
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAdress;
    private String logradouro;
    private String bairro;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;
    private Long fkClient;

    public Adress(String logradouro, String bairro, String complemento, String cep, String cidade, String estado) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
