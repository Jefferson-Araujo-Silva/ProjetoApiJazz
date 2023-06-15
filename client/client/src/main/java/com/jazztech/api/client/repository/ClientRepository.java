package com.jazztech.api.client.repository;

import com.jazztech.api.client.repository.entity.ClientEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {
    List<ClientEntity> findByCpf(String cpf);
}
