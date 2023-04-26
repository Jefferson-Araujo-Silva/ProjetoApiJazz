package com.jazztech.apicadastro.infrastructure.repository;

import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository <Clients, Integer> {

    byte countByCpfContains(String cpf);
    Optional<Clients> findByCpfContains(String cpf);

    @Query("SELECT c, a FROM Clients c JOIN c.adress a")
    List<Object[]> findPessoaAndEnderecoById();
}
