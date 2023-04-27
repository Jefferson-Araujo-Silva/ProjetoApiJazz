package com.jazztech.apicadastro.applicationservice.clientsService;

import com.jazztech.apicadastro.infrastructure.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class DeleteClients {
    private final ClientRepository clientRepository;

    public HttpStatus delete (@PathVariable int id){
        clientRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
