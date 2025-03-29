package org.alianza.backendalianza_fo.service.implementation;

import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.alianza.backendalianza_fo.dto.ClientDto;
import org.alianza.backendalianza_fo.dto.MessageDto;
import org.alianza.backendalianza_fo.jpa.entity.ClientEntity;
import org.alianza.backendalianza_fo.jpa.repository.ClientRepository;
import org.alianza.backendalianza_fo.service.IClientService;
import org.alianza.backendalianza_fo.utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.List;

/**
 * Service implementation for client-related operations.
 *
 */
@Service
@Slf4j
public class ClientService implements IClientService {

    @Autowired
    ClientRepository clientRepository;

    /**
     * Retrieves all clients from the database.
     *
     * @return a list of {@link ClientDto} representing all clients.
     */
    @Override
    public List<ClientDto> getAllClients() {
        log.info(Constants.LOG_START_SEARCHING_ALL_CLIENTS);
        List<ClientEntity> listEntities = clientRepository.findAll();
        List<ClientDto> listDTOs = listEntities.stream()
                .map(this::generateDTO)
                .toList();
        return listDTOs;
    }

    /**
     * Retrieves clients whose shared key contains the given string.
     *
     * @param sharedKey partial or full shared key to search.
     * @return a list of {@link ClientDto} matching the search.
     */
    @Override
    public List<ClientDto> getClientsBySharedKey(String sharedKey){
        log.info(String.format(Constants.LOG_START_SEARCHING_CLIENTS_BY_SHARED_KEY, sharedKey));
        List<ClientEntity> listEntities = clientRepository.findBySharedKeyContaining(sharedKey);
        List<ClientDto> listDTOs = listEntities.stream()
                .map(this::generateDTO)
                .toList();
        log.info(String.format(Constants.LOG_RESULT_SEARCHING_CLIENTS_BY_SHARED_KEY, listDTOs.size(), sharedKey));
        return listDTOs;
    }
    /**
     * Creates a new client with an automatically generated shared key.
     *
     * @param clientDto client data to create.
     * @return a {@link MessageDto} indicating the result.
     * @throws ParseException if date parsing fails.
     */
    @Override
    public MessageDto createClient(ClientDto clientDto) throws ParseException {
        ClientEntity clientEntity = this.generateEntity(clientDto);
        clientEntity.setSharedKey(this.generateSharedKey(
                clientDto.getName(), clientDto.getLastName()
            ));
        clientRepository.save(clientEntity);
        log.info(String.format(Constants.LOG_USER_CREATED, clientEntity.getId()));
        return new MessageDto(Constants.MESSAGE_CLIENT_CREATED_SUCCESFULL, false);
    }

    /**
     * Generates a shared key based on the first character of the name and the last name.
     * Appends a numeric suffix if the generated shared key already exists.
     *
     * @param name client's first name.
     * @param lastName client's last name.
     * @return unique shared key.
     */
    private String generateSharedKey(String name, String lastName) {
        String sharedKey = name.charAt(0) + lastName;
        List<ClientEntity> listEntitiesBySharedKey = clientRepository.findBySharedKey(sharedKey);
        if(listEntitiesBySharedKey.isEmpty()){
            return sharedKey;
        }else {
            return sharedKey+listEntitiesBySharedKey.size();
        }
    }

    /**
     * Maps a {@link ClientEntity} to a {@link ClientDto}.
     *
     * @param clientEntity entity to map.
     * @return mapped DTO.
     */
    private ClientDto generateDTO(ClientEntity clientEntity) {
        ClientDto clientDto = new ClientDto();
        clientDto.setSharedKey(clientEntity.getSharedKey());
        clientDto.setBusinessId(clientEntity.getBusinessId());
        clientDto.setEmail(clientEntity.getEmail());
        clientDto.setPhone(clientEntity.getPhone());
        clientDto.setDataStart(clientEntity.getDataStart());
        clientDto.setDataFinish(clientEntity.getDataFinish());
        return clientDto;
    }

    /**
     * Maps a {@link ClientDto} to a {@link ClientEntity}.
     *
     * @param clientDto DTO to map.
     * @return mapped entity.
     * @throws ParseException if date conversion fails.
     */
    private ClientEntity generateEntity(ClientDto clientDto) throws ParseException {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setBusinessId(clientDto.getBusinessId());
        clientEntity.setEmail(clientDto.getEmail());
        clientEntity.setPhone(clientDto.getPhone());
        clientEntity.setDataStart(clientDto.getDataStart());
        clientEntity.setDataFinish(clientDto.getDataFinish());
        return clientEntity;
    }

    /**
     * Generates a file CSV with the information about CLients
     */
    public byte[] exportCSV(){
        log.info(Constants.LOG_FILE_CSV_GENERATED);
        List<ClientDto> listEntities = this.getAllClients();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(outputStream))) {
            writer.writeNext(new String[] {"Shared Key", "Business ID", "Email", "Phone", "Data Added" });

            for (ClientDto client : listEntities) {
                writer.writeNext(new String[] {
                        client.getSharedKey(),
                        client.getBusinessId(),
                        client.getEmail(),
                        client.getPhone(),
                        client.getDataStart().toString()
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputStream.toByteArray();
    }
}