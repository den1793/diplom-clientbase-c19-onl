package by.clientbase.diplomclientbasec19onl.service;

import by.clientbase.diplomclientbasec19onl.dto.ClientDTO;
import by.clientbase.diplomclientbasec19onl.entity.Client;
import by.clientbase.diplomclientbasec19onl.mapper.ClientMapper;
import by.clientbase.diplomclientbasec19onl.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Denis Smirnov on 14.06.2023
 */
@Service
public class ClientService {

    private ClientRepository clientRepository;

    ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /*public Client save(ClientDTO clientDTO) {
        Client client = Client.builder()
                .clientName(clientDTO.getClientName())
                .addresses(List.of(Address.builder()
                        .street(clientDTO.getAddress())
                        .build()))
                .description(clientDTO.getDescription())
                .build();
        return clientRepository.save(client);
    }*/
    public void save(ClientDTO clientDTO) {
        Client client = ClientMapper.toClient(clientDTO);
        clientRepository.save(client);
    }



    /*public boolean save(ClientDTO clientDTO) {
        Client client = ClientMapper.toClient(clientDTO);
        clientRepository.save(client);
        return true;
    }*/

    public Client findByClientName(String clientName) {
        return clientRepository.findByClientName(clientName);
    }

    public List<Client> findAll() {
        return  clientRepository.findAll();

    }

    /*public List<Client> findAll() {
        return clientRepository.findAll();
    }*/


}
