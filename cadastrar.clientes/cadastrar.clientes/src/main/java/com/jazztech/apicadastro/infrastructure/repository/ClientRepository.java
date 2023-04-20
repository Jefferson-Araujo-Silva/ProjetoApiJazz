package com.jazztech.apicadastro.infrastructure.repository;

import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository <Clients, Long> {
    @Query("SELECT c, a FROM Clients c JOIN c.adress a")
    List<Object[]> findPessoaAndEnderecoById();
}
