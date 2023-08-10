package by.clientbase.diplomclientbasec19onl.repository;

import by.clientbase.diplomclientbasec19onl.entity.Client;
import by.clientbase.diplomclientbasec19onl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Denis Smirnov on 14.06.2023
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByClientName(String clientName);
}
