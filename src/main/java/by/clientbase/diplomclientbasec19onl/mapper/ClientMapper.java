package by.clientbase.diplomclientbasec19onl.mapper;

import by.clientbase.diplomclientbasec19onl.dto.ClientDTO;
import by.clientbase.diplomclientbasec19onl.dto.TaskDTO;
import by.clientbase.diplomclientbasec19onl.dto.UserRegistrationDTO;
import by.clientbase.diplomclientbasec19onl.entity.Address;
import by.clientbase.diplomclientbasec19onl.entity.Client;
import by.clientbase.diplomclientbasec19onl.entity.Task;
import by.clientbase.diplomclientbasec19onl.entity.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Denis Smirnov on 21.06.2023
 */
public class ClientMapper {

    public static Client toClient(ClientDTO clientDTO) {
        return Client.builder()
                .clientName(clientDTO.getClientName())
                .addresses(List.of(Address.builder()
                        .street(clientDTO.getAddress())
                        .build()))
                .description(clientDTO.getDescription())
                .build();

    }

    public static ClientDTO toDto (Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        client.setClientName(client.getClientName());
        client.setAddresses(client.getAddresses());
        return clientDTO;
    }
}
