package com.jazztech.api.client.service;

import com.jazztech.api.client.apiclient.ViaCepApiClient;
import com.jazztech.api.client.apiclient.addressdto.AddressViaCep;
import com.jazztech.api.client.controller.request.ClientRequest;
import com.jazztech.api.client.controller.response.ClientResponse;
import com.jazztech.api.client.exception.ClientNotFoundException;
import com.jazztech.api.client.mapper.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ClientServiceTest {
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
        ArgumentCaptor<String> cpfArgumentCapor = ArgumentCaptor.forClass(String.class);
        when(clientRepository.findByCpf(cpfArgumentCapor.capture())).thenReturn(clientEntityFactory());

        final ClientRequest clientRequest = clientRequestFactory();
        final ClientResponse clientResponse = clientService.getClientBy("53887957806");
        assertEquals(clientRequest.cpf(), clientResponse.cpf());
        assertEquals(clientRequest.name(), clientResponse.name());
        assertEquals(clientRequest.address().cep(), clientResponse.address().cep());
        assertEquals(clientRequest.address().complement(), clientResponse.address().complement());
    }
    @Test
    void should_return_client_where_id_is_idClientEntity(){
        ArgumentCaptor<UUID> idrgumentCaptor = ArgumentCaptor.forClass(UUID.class);
        when(clientRepository.findById(idrgumentCaptor.capture())).thenReturn(Optional.ofNullable(clientEntityFactory()));


        final ClientEntity clientRequest = clientEntityFactory();
        UUID idRequest = clientRequest.getId();
        final ClientResponse clientResponse = clientService.getClientBy(idRequest);

        assertEquals(clientRequest.getCpf(), clientResponse.cpf());
        assertEquals(clientRequest.getName(), clientResponse.name());
        assertEquals(clientRequest.getAddress().getCep(), clientResponse.address().cep());
        assertEquals(clientRequest.getAddress().getComplement(), clientResponse.address().complement());
    }

    @Test
    void should_throw_client_not_found_exception() {
        ArgumentCaptor<UUID> idrgumentCaptor = ArgumentCaptor.forClass(UUID.class);
        Optional<ClientEntity> clientEntity = null;
        when(clientRepository.findById(idrgumentCaptor.capture())).thenThrow(ClientNotFoundException.class);

        UUID idRequest = UUID.randomUUID();
        ClientNotFoundException clientNotFoundException = assertThrows(ClientNotFoundException.class,
                ()-> clientService.getClientBy(idRequest));
        assertEquals(String.format("Client not found by id %s", idRequest), clientNotFoundException.getMessage());
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
    public static ClientResponse clientResponseFactory() {
        return ClientResponse.builder()
                .id(UUID.randomUUID())
                .cpf("53887957806")
                .name("Jeff tester")
                .dateOfBirth(LocalDate.now().minusYears(20))
                .address(ClientResponse.AddressResponse.builder()
                        .numberOfHouse(11)
                        .cep("08466-010")
                        .complement("n/a")
                        .build())
                .build();
    }
    public static ClientRequest clientRequestFactory() {
        return ClientRequest.builder()
                .cpf("53887957806")
                .name("Jeff tester")
                .dateOfBirth(LocalDate.now().minusYears(20))
                .address(ClientRequest.AddressRequest.builder()
                        .numberOfHouse(11)
                        .cep("08466-010")
                        .complement("n/a")
                        .build())
                .build();
    }

    public static AddressViaCep addressViaCepFactory() {
        return AddressViaCep.builder()
                .cep("08466-010")
                .logradouro("Rua Raul Seixas")
                .complemento("n/a")
                .bairro("Guaianases")
                .uf("SP")
                .build();
    }

    public static ClientEntity clientEntityFactory() {
        return ClientEntity.builder()
                .name("Jeff tester")
                .cpf("53887957806")
                .dateOfBirth(LocalDate.now().minusYears(20))
                .address(AddressEntity.builder()
                        .state("SP")
                        .cep("08466-010")
                        .neighborhood("Guaianases")
                        .complement("n/a")
                        .street("Rua Raul Seicas")
                        .uf("SP")
                        .build())
                .build();
    }
}