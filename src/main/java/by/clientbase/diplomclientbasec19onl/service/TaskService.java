package by.clientbase.diplomclientbasec19onl.service;

import by.clientbase.diplomclientbasec19onl.dto.TaskDTO;
import by.clientbase.diplomclientbasec19onl.entity.Task;
import by.clientbase.diplomclientbasec19onl.entity.User;
import by.clientbase.diplomclientbasec19onl.mapper.TaskMapper;
import by.clientbase.diplomclientbasec19onl.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    private final Logger log = Logger.getLogger(TaskService.class.getName());


    public boolean save(TaskDTO taskDTO) {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) loggedInUser.getPrincipal();

        Task task = TaskMapper.INSTANCE.dtoToTask(taskDTO);

        task.setCreator(user);
        task.setCreatedAt(LocalDate.now());
        task.setPerformer(taskDTO.getPerformer());
        task.setClient(taskDTO.getClient());
        task.setStatus(taskDTO.getStatus());
        task.setDescription(taskDTO.getDescription());


        taskRepository.save(task);
        log.log(Level.INFO, "Task saved " + task.getId());

        return true;
    }

    public Optional<Task> findById(long id) {
        return taskRepository.findById(id);
    }


    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Page<Task> findAllPaginated(int page, int size) {
        return taskRepository.findAllPaginated(PageRequest.of(page-1, size));
    }


    public Page<Task> findByUser(int page, int size) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) loggedInUser.getPrincipal();
        return taskRepository.findByPerformer(user.getId(), PageRequest.of(page-1, size));
    }


    public boolean update(TaskDTO taskDTO, long id) {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) loggedInUser.getPrincipal();

        Optional<Task> existTask = taskRepository.findById(id);

        if (existTask.isPresent()) {
            Task task = TaskMapper.INSTANCE.updateTaskDtoToTask(taskDTO, existTask.get().getId());

            task.setPerformer(taskDTO.getPerformer());
            task.setCreatedAt(existTask.get().getCreatedAt());
            task.setCreator(existTask.get().getCreator());
            task.setClient(existTask.get().getClient());
            task.setStatus(taskDTO.getStatus());
            task.setDescription(taskDTO.getDescription());
            taskRepository.save(task);
            return true;
        } else {
            return false;
        }
    }

    public void deleteTaskById(long id) {
        taskRepository.deleteById(id);
    }
}
