package by.clientbase.diplomclientbasec19onl.repository;

import by.clientbase.diplomclientbasec19onl.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById (Long id);

    boolean existsById (Long id);

    @Query("select p from Task p")
    Page<Task> findAllPaginated(Pageable pageable);

    @Query("select t from Task t where t.performer.id = ?1")
    Page<Task> findByPerformer(Long id, Pageable pageable);



}
