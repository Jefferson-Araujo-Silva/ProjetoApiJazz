package com.jazztech.api.client.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;
@Table(name = "Address")
@Entity
@Immutable
public class AddressEntity {

    @Id
    UUID id;
    String cep;
    String street;
    String complement;
    String neighborhood;
    String state;
    String uf;
    Integer numberOfHouse;
    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    private AddressEntity() {
    }

    @Builder
    public AddressEntity(String street, String neighborhood, String uf, String state, Integer numberOfHouse, String complement, String cep) {
        this.id = UUID.randomUUID();
        this.street = street;
        this.neighborhood = neighborhood;
        this.uf = uf;
        this.state = state;
        this.numberOfHouse = numberOfHouse;
        this.complement = complement;
        this.cep = cep;
    }

    public UUID getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getStreet() {
        return street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getState() {
        return state;
    }

    public String getUf() {
        return uf;
    }

    public Integer getNumberOfHouse() {
        return numberOfHouse;
    }

    public String getComplement() {
        return complement;
    }
}