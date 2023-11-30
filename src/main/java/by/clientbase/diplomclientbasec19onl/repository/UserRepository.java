package by.clientbase.diplomclientbasec19onl.repository;

import by.clientbase.diplomclientbasec19onl.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findById (Long id);

    @Query("select p from User p")
    Page<User> findAllPaginated(String username, Pageable pageable);

    List<User> findAllByName (String username, Sort sort);




}
