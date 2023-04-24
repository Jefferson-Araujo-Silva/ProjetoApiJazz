package com.jazztech.apicadastro.infrastructure.apiClients;

import com.jazztech.apicadastro.applicationservice.domain.entity.Adress;
import com.jazztech.apicadastro.infrastructure.apiClients.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ViaCepApiClient", url = "https://viacep.com.br/ws/")
public interface ViaCepApiClient {

    @GetMapping("{cep}/json/")
    public ClientDto createAdress(@PathVariable String cep);
}
