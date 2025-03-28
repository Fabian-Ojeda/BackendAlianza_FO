package org.alianza.backendalianza_fo.controller;

import lombok.RequiredArgsConstructor;
import org.alianza.backendalianza_fo.dto.ClientDto;
import org.alianza.backendalianza_fo.dto.MessageDto;
import org.alianza.backendalianza_fo.service.IClientService;
import org.alianza.backendalianza_fo.utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
/**
 * REST Controller for managing clients.
 *
 * Provides endpoints to retrieve all clients, search clients by shared key, and create new clients.
 * Uses the {@link IClientService} to handle the business logic.
 *
 * Base URL: {@link Constants#BASE_URL_CLIENTS}
 */
@RestController
@RequestMapping(Constants.BASE_URL_CLIENTS)
@RequiredArgsConstructor
public class ClientController {

    @Autowired
    IClientService clientService;

    /**
     * Retrieves all registered clients.
     *
     * @return ResponseEntity containing the list of {@link ClientDto} and HTTP status 200 (OK).
     */
    @GetMapping(Constants.PATH_GET_ALL_CLIENTS)
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    /**
     * Retrieves clients that contain the specified shared key.
     *
     * @param sharedKey the shared key used to filter clients.
     * @return ResponseEntity containing the list of matching {@link ClientDto} and HTTP status 200 (OK).
     */
    @GetMapping(Constants.PATH_GET_CLIENTS_BY_SHARED_KEY)
    public ResponseEntity<List<ClientDto>> getClientsBySharedKey(@PathVariable String sharedKey) {
        return new ResponseEntity<>(clientService.getClientsBySharedKey(sharedKey), HttpStatus.OK);
    }

    /**
     * Creates a new client in the system.
     *
     * @param clientDto the client data to be created.
     * @return ResponseEntity containing a {@link MessageDto} with the result message and HTTP status 200 (OK).
     * @throws ParseException if there is an error while parsing dates or other data.
     */
    @PostMapping(value=Constants.PATH_CREATE_CLIENTS, consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageDto> create(@RequestBody ClientDto clientDto) throws ParseException {
        return new ResponseEntity<>(clientService.createClient(clientDto), HttpStatus.OK);
    }

}
