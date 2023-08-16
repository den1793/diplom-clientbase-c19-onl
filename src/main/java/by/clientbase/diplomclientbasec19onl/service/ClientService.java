package by.clientbase.diplomclientbasec19onl.service;

import by.clientbase.diplomclientbasec19onl.dto.ClientDTO;
import by.clientbase.diplomclientbasec19onl.entity.Client;
import by.clientbase.diplomclientbasec19onl.entity.Task;
import by.clientbase.diplomclientbasec19onl.mapper.ClientMapper;
import by.clientbase.diplomclientbasec19onl.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Denis Smirnov on 14.06.2023
 */
@Service
public class ClientService {

    private ClientRepository clientRepository;

    private final Logger log = Logger.getLogger(ClientService.class.getName());

    ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void save(ClientDTO clientDTO) {
        Client client = ClientMapper.toClient(clientDTO);
        clientRepository.save(client);
        log.log(Level.INFO, "Client saved " + clientDTO.getClientName());

    }

    public Client findByClientName(String clientName) {
        return clientRepository.findByClientName(clientName);
    }

    public List<Client> findAll() {
        return  clientRepository.findAll();

    }

    public Optional<Client> findById(long id) {
        return clientRepository.findById(id);
    }

    public boolean update(Client client, long id) {


        Client existClient = clientRepository.getById(id);

        boolean checkParam = false;

        if (client.getClientName() != null) {
            existClient.setClientName(client.getClientName());
            checkParam = true;
        }

        if (client.getAddresses() != null) {
            existClient.setAddresses(client.getAddresses());
            checkParam = true;
        }

        if (client.getDescription() != null) {
            existClient.setDescription(client.getDescription());
            checkParam = true;
        }
        if (checkParam) {
            clientRepository.save(existClient);
            return true;
        }
        return false;
    }

    public void deleteClientById(long id) {
        clientRepository.deleteById(id);
    }


}
