package com.jazztech.api.client.service;

import com.jazztech.api.client.apiclient.ViaCepApiClient;
import com.jazztech.api.client.apiclient.addressdto.AddressViaCep;
import com.jazztech.api.client.controller.request.ClientRequest;
import com.jazztech.api.client.controller.response.ClientResponse;
import com.jazztech.api.client.mapper.*;
import com.jazztech.api.client.model.Address;
import com.jazztech.api.client.model.Client;
import com.jazztech.api.client.repository.ClientRepository;
import com.jazztech.api.client.repository.entity.AddressEntity;
import com.jazztech.api.client.repository.entity.ClientEntity;
import com.jazztech.api.client.services.ClientService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ClientServiceTeste {
    @Mock
    private ViaCepApiClient viaCepApiClient;
    @Mock
    private ClientRepository clientRepository;
    @Spy
    private ClientEntityMapper clientEntityMapper = new ClientEntityMapperImpl();
    @Spy
    private ClientResponseMapper clientResponseMapper = new ClientResponseMapperImpl();
    @Spy
    private ClientMapper clientMapper = new ClientMapperImpl();

    @InjectMocks
    private ClientService clientService;

    @Captor
    private ArgumentCaptor<String> cepArgumentCaptor;

    @Captor
    private ArgumentCaptor<ClientEntity> clientEntityArgumentCaptor;


    @Test
    void should_create_client() {
        final AddressViaCep addressViaCep = addressViaCepFactory();
        when(viaCepApiClient.getAddress(cepArgumentCaptor.capture())).thenReturn(addressViaCep);
        when(clientRepository.save(clientEntityArgumentCaptor.capture())).thenReturn(clientEntityFactory());

        final ClientRequest clientRequest = clientRequestFactory();
        final ClientResponse clientResponse = clientService.create(clientRequest);

        assertNotNull(clientResponse);
        assertNotNull(clientResponse.id());

        assertEquals(clientRequest.address().cep(), cepArgumentCaptor.getValue());
        assertEquals(clientRequest.cpf(), clientResponse.cpf());
        assertEquals(clientRequest.name(), clientResponse.name());

        final ClientEntity clientEntity = clientEntityArgumentCaptor.getValue();
        assertEquals(clientRequest.cpf(), clientEntity.getCpf());
        assertEquals(clientRequest.name(), clientEntity.getName());
        assertEquals(clientRequest.dateOfBirth(), clientEntity.getDateOfBirth());
    }

    // TODO fazer os testes para o caso de uso de consulta
    @Test
    void should_return_client_where_cpf_is_6027943005(){

        ArgumentCaptor<String> cpfArgumentCaptor = ArgumentCaptor.forClass(String.class);
        when(clientRepository.findByCpf(cpfArgumentCaptor.capture())).thenReturn(clientEntityFactory());

        final ClientRequest clientRequest = clientRequestFactory();
        final ClientEntity clientResponse = clientRepository.findByCpf("53887957806");
        assertEquals(clientRequest.cpf(), clientResponse.getCpf());
        assertEquals(clientRequest.name(), clientResponse.getName());
        assertEquals(clientRequest.address().cep(), clientResponse.getAddress().getCep());
        assertEquals(clientRequest.address().complement(), clientResponse.getAddress().getComplement());
    }
    @Test
    void should_return_address(){
        final AddressViaCep addressRequest = addressViaCepFactory();
        when(clientService.getAddressViaCep(cepArgumentCaptor.capture())).thenReturn(addressRequest);

        final AddressViaCep addressResponse = clientService.getAddressViaCep("01001-000");

        assertEquals(addressRequest.bairro(), addressResponse.bairro());
        assertEquals(addressRequest.cep(), addressResponse.cep());
        assertEquals(addressRequest.uf(), addressResponse.uf());
        assertEquals(addressRequest.logradouro(), addressResponse.logradouro());
    }

    public static ClientRequest clientRequestFactory() {
        return ClientRequest.builder()
                .cpf("60279437005")
                .name("Elin Williams")
                .dateOfBirth(LocalDate.now().minusYears(20))
                .address(ClientRequest.AddressRequest.builder()
                        .numberOfHouse(101)
                        .cep("01001-000")
                        .complement("complement")
                        .build())
                .build();
    }

    public static AddressViaCep addressViaCepFactory() {
        return AddressViaCep.builder()
                .cep("01001-000")
                .logradouro("Praça da Sé")
                .complemento("lado ímpar")
                .bairro("Sé")
                .uf("SP")
                .build();
    }

    public static ClientEntity clientEntityFactory() {
        return ClientEntity.builder()
                .name("Elin Williams")
                .cpf("60279437005")
                .dateOfBirth(LocalDate.now().minusYears(20))
                .address(AddressEntity.builder()
                        .state("SP")
                        .cep("01001-000")
                        .neighborhood("Sé")
                        .complement("complement")
                        .street("Praça da Sé")
                        .uf("São Paulo")
                        .build())
                .build();
    }
}