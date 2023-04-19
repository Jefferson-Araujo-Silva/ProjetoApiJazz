package com.jazztech.apicadastro.applicationservice.domain.entity;

import com.gtbr.domain.Cep;
import jakarta.persistence.*;
import lombok.experimental.NonFinal;

@Entity
@Table(name = "adress")
public class Adress{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @NonFinal
        private Integer idAdress;
        private String logradouro;
        private String bairro;
        private String complemento;
        private String cep;
        private String cidade;
        private String estado;

        @Transient
        private Clients clients;

        public Adress(){}
    public Adress(Cep cep, Clients clients) {
        this.bairro = cep.getBairro();
        this.cep = cep.getCep();
        this.cidade = cep.getLocalidade();
        this.estado = cep.getUf();
        this.complemento = clients.getComplement();
        this.logradouro = cep.getLogradouro();
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
