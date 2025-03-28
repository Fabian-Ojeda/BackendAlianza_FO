package org.alianza.backendalianza_fo.service;

import org.alianza.backendalianza_fo.dto.ClientDto;
import org.alianza.backendalianza_fo.dto.MessageDto;

import java.text.ParseException;
import java.util.List;
/**
 * Interface for client service operations.
 *
 * This interface defines the contract for managing clients,
 * including retrieving all clients, searching by shared key,
 * and creating new clients.
 */
public interface IClientService {
    /**
     * Retrieves all clients stored in the system.
     *
     * @return a list of {@link ClientDto} containing all clients.
     */
    public List<ClientDto> getAllClients();

    /**
     * Retrieves clients whose shared key contains the specified string.
     *
     * @param sharedKey partial or full shared key to search for.
     * @return a list of {@link ClientDto} matching the search criteria.
     */
    public List<ClientDto> getClientsBySharedKey(String sharedKey);

    /**
     * Creates a new client with the provided information.
     * The shared key will be generated automatically.
     *
     * @param clientDto the client data to be created.
     * @return a {@link MessageDto} indicating the success or failure of the operation.
     * @throws ParseException if any error occurs while parsing data.
     */
    public MessageDto createClient(ClientDto clientDto) throws ParseException;
}
