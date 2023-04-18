package com.jazztech.apicadastro.infrastructure.repository;

import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository <Clients, Long> {
}
