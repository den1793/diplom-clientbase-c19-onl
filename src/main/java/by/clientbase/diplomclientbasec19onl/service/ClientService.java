package by.clientbase.diplomclientbasec19onl.service;

import by.clientbase.diplomclientbasec19onl.dto.ClientDTO;
import by.clientbase.diplomclientbasec19onl.entity.Client;
import by.clientbase.diplomclientbasec19onl.mapper.ClientMapper;
import by.clientbase.diplomclientbasec19onl.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
@Transactional
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;


    private final Logger log = Logger.getLogger(ClientService.class.getName());


    public boolean save(ClientDTO clientDTO) {

        Client client = ClientMapper.INSTANCE.dtoToClient(clientDTO);

        clientRepository.save(client);
        log.log(Level.INFO, "Client saved " + clientDTO.getClientName());
        return true;
    }

    public Optional<Client> findByClientName(String clientName) {
        return clientRepository.findByClientName(clientName);
    }

    public List<Client> findAll() {
        return clientRepository.findAll(Sort.by("clientName").ascending());
    }

    public Optional<Client> findById(long id) {
        return clientRepository.findById(id);
    }

    public Page<Client> findAllPaginated(String clientName, int page, int size) {
        return clientRepository.findAllPaginated(clientName, PageRequest.of(page-1, size, Sort.by("clientName").ascending()));
    }
    public boolean update(ClientDTO clientDTO, long id) {

        Optional<Client> existClient = clientRepository.findById(id);

        if (existClient.isPresent()) {
            Client client = ClientMapper.INSTANCE.updateClientDtoToClient(clientDTO, existClient.get().getId());
            clientRepository.save(client);
            return true;
        } else {
            return false;
        }
    }

    public void deleteClientById(long id) {
        clientRepository.deleteById(id);
    }


}
