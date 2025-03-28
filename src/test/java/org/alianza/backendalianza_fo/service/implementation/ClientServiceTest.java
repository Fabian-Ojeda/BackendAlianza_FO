package org.alianza.backendalianza_fo.service.implementation;

import org.alianza.backendalianza_fo.dto.ClientDto;
import org.alianza.backendalianza_fo.dto.MessageDto;
import org.alianza.backendalianza_fo.jpa.entity.ClientEntity;
import org.alianza.backendalianza_fo.jpa.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void getAllClients() {

        ClientEntity entity = new ClientEntity();
        entity.setSharedKey("JSmith");
        entity.setBusinessId("B123");
        entity.setEmail("test@example.com");
        entity.setPhone("123456");
        entity.setDataStart(new Date());
        entity.setDataFinish(new Date());
        when(clientRepository.findAll()).thenReturn(
                List.of(entity)
        );

        List<ClientDto> result = clientService.getAllClients();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("JSmith", result.get(0).getSharedKey());
    }

    @Test
    void getClientsBySharedKey() {
        String sharedKey = "Smith";
        ClientEntity entity = new ClientEntity();
        entity.setSharedKey("JSmith");

        when(clientRepository.findBySharedKeyContaining(sharedKey))
                .thenReturn(List.of(entity));

        List<ClientDto> result = clientService.getClientsBySharedKey(sharedKey);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("JSmith", result.get(0).getSharedKey());
    }

    @Test
    void createClient() throws ParseException {
        ClientDto clientDto = new ClientDto();
        clientDto.setName("John");
        clientDto.setLastName("Smith");
        clientDto.setBusinessId("B123");
        clientDto.setEmail("john@example.com");
        clientDto.setPhone("123456");
        clientDto.setDataStart(new Date());
        clientDto.setDataFinish(new Date());

        when(clientRepository.findBySharedKey("JSmith"))
                .thenReturn(Collections.emptyList());

        MessageDto result = clientService.createClient(clientDto);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isError());
        Assertions.assertEquals("Client created successfully", result.getMessage());
    }

    @Test
    void createClientSharedKeyRepeated() throws ParseException {
        ClientDto clientDto = new ClientDto();
        clientDto.setName("John");
        clientDto.setLastName("Smith");
        clientDto.setBusinessId("B123");
        clientDto.setEmail("john@example.com");
        clientDto.setPhone("123456");
        clientDto.setDataStart(new Date());
        clientDto.setDataFinish(new Date());

        ClientEntity clientEntity = new ClientEntity();

        when(clientRepository.findBySharedKey("JSmith"))
                .thenReturn(List.of(clientEntity));

        MessageDto result = clientService.createClient(clientDto);

        assertNotNull(result);
        assertFalse(result.isError());
        assertEquals("Client created successfully", result.getMessage());
    }
}