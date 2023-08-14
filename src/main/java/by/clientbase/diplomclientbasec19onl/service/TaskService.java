package by.clientbase.diplomclientbasec19onl.service;

import by.clientbase.diplomclientbasec19onl.dto.TaskDTO;
import by.clientbase.diplomclientbasec19onl.dto.UserRegistrationDTO;
import by.clientbase.diplomclientbasec19onl.entity.Client;
import by.clientbase.diplomclientbasec19onl.entity.Task;
import by.clientbase.diplomclientbasec19onl.entity.User;
import by.clientbase.diplomclientbasec19onl.mapper.TaskMapper;
import by.clientbase.diplomclientbasec19onl.mapper.UserMapper;
import by.clientbase.diplomclientbasec19onl.repository.TaskRepository;
import by.clientbase.diplomclientbasec19onl.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Denis Smirnov on 14.06.2023
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserService userService;

    private final ClientService clientService;

    TaskService(TaskRepository taskRepository, UserService userService, ClientService clientService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.clientService = clientService;
    }


    public Task save(TaskDTO taskDTO) {
        Task task = Task.builder()
                .creator(userService.findByUsername(taskDTO.getCreator()))
                .performer(userService.findByUsername(taskDTO.getPerformer()))
                .client(clientService.findByClientName(taskDTO.getClient()))
                .description(taskDTO.getDescription())
                .build();

        return taskRepository.save(task);
    }


    public Optional<Task> findById(long id) {
        return taskRepository.findById(id);
    }


    public List<Task> findAll() {
        return taskRepository.findAll();
    }



    public boolean update(Task task, long id) {

        Task existTask = taskRepository.getById(id);

        boolean checkParam = false;
        if (task.getCreator() != null) {
            existTask.setCreator(task.getCreator());
            checkParam = true;
        }

        if (task.getPerformer() != null) {
            existTask.setPerformer(task.getPerformer());
            checkParam = true;
        }
        if (task.getClient() != null) {
            existTask.setClient(task.getClient());
            checkParam = true;
        }
        if (task.getDescription() != null) {
            existTask.setDescription(task.getDescription());
            checkParam = true;
        }
        return false;
    }


}
