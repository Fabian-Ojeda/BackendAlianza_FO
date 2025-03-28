package org.alianza.backendalianza_fo.controller;

import org.alianza.backendalianza_fo.dto.ClientDto;
import org.alianza.backendalianza_fo.dto.MessageDto;
import org.alianza.backendalianza_fo.service.IClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private IClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @Test
    void getAllClients() {
        ClientDto client = new ClientDto();
        client.setSharedKey("JSmith");
        List<ClientDto> clients = List.of(client);

        when(clientService.getAllClients()).thenReturn(clients);

        ResponseEntity<List<ClientDto>> response = clientController.getAllClients();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("JSmith", response.getBody().get(0).getSharedKey());
    }

    @Test
    void getClientsBySharedKey() {
        String sharedKey = "Smith";
        ClientDto client = new ClientDto();
        client.setSharedKey("JSmith");
        List<ClientDto> clients = List.of(client);

        when(clientService.getClientsBySharedKey(sharedKey)).thenReturn(clients);

        ResponseEntity<List<ClientDto>> response = clientController.getClientsBySharedKey(sharedKey);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("JSmith", response.getBody().get(0).getSharedKey());
    }

    @Test
    void getClientsBySharedKeyListEmpty() {
        String sharedKey = "Smith";

        when(clientService.getClientsBySharedKey(sharedKey)).thenReturn(new ArrayList<>());

        ResponseEntity<List<ClientDto>> response = clientController.getClientsBySharedKey(sharedKey);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void create() throws ParseException {

        ClientDto clientDto = new ClientDto();
        clientDto.setName("John");
        clientDto.setLastName("Smith");

        MessageDto messageDto = new MessageDto("Client created successfully", false);

        when(clientService.createClient(any(ClientDto.class))).thenReturn(messageDto);

        ResponseEntity<MessageDto> response = clientController.create(clientDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Client created successfully", response.getBody().getMessage());
        assertFalse(response.getBody().isError());
    }
}