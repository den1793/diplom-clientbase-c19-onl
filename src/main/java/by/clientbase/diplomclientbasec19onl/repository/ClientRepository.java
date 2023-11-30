package by.clientbase.diplomclientbasec19onl.repository;

import by.clientbase.diplomclientbasec19onl.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;



public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByClientName(String clientName);

    Optional<Client> findById (Long id);

    @Query("select p from Client p")
    Page<Client> findAllPaginated(String clientName, Pageable pageable);


}
