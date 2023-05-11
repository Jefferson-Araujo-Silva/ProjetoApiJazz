package com.jazztech.api.client.repository;

import com.jazztech.api.client.repository.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {
    ClientEntity findByCpf(String cpf);
}
