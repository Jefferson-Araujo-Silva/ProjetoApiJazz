package com.jazztech.api.client.repository.entity;

import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "Client")
@Entity
@Immutable
public class ClientEntity {
    @Id
    UUID id;
    String name;
    String cpf;
    LocalDate dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    AddressEntity address;
    @CreationTimestamp
    @Column(name = "createdAt")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    LocalDateTime updatedAt;

    private ClientEntity() {
    }


    @Builder
    public ClientEntity(String name, String cpf, LocalDate dateOfBirth, AddressEntity address) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public AddressEntity getAddress() {
        return address;
    }
}
