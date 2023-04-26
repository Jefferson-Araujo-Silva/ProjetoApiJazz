package com.jazztech.apicadastro.applicationservice.clientsService;

import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import com.jazztech.apicadastro.infrastructure.repository.ClientRepository;
import com.jazztech.apicadastro.presentation.handler.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchClients {
    private final ClientRepository clientRepository;

    public List<Clients> listAll(){
        return clientRepository.findAll();
    }

    public List<Object[]> findPessoaAndAdressById(Integer idAdress){
        return clientRepository.findPessoaAndEnderecoById();
    }

    public Clients findById(Integer id) throws ClientNotFoundException {
        return clientRepository.findById(id).orElseThrow(
                () -> new ClientNotFoundException(String.format("Usuário com id '%d' não existe",
                        id))
        );

    }
}
