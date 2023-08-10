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
//    public Task save(TaskDTO taskDTO) {
//        Task task = TaskMapper.toTask(taskDTO);
//        return taskRepository.save(task);
//    }

    /*public boolean save(TaskDTO taskDTO) {
        Task task = TaskMapper.toTask(taskDTO);
        taskRepository.save(task);
        return true;
    }*/

    public Optional<Task> findById(long id) {
        return taskRepository.findById(id);
    }

//    public List<Task> findAll() {
//        return taskRepository.findAll();
//    }

    /*public List<TaskDTO> findAll() {
        List<TaskDTO> taskDTOList = TaskMapper.mapFromTaskDTOListFromTasks(taskRepository.findAll());
        return taskDTOList;
    }*/

    public List<Task> findAll() {
        return taskRepository.findAll();

    }

}
