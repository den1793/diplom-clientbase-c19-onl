package by.clientbase.diplomclientbasec19onl.mapper;

import by.clientbase.diplomclientbasec19onl.dto.TaskDTO;
import by.clientbase.diplomclientbasec19onl.entity.Task;
import by.clientbase.diplomclientbasec19onl.service.ClientService;

import by.clientbase.diplomclientbasec19onl.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Denis Smirnov on 16.06.2023
 */
public class TaskMapper {

    private static UserService userService;
    private static ClientService clientService;


    public TaskMapper(UserService userService, ClientService clientService) {
        this.userService = userService;
        this.clientService = clientService;

    }

    public static Task toTask(TaskDTO taskDTO) {
        return Task.builder()
                .creator(userService.findByUsername(taskDTO.getCreator()))
                .performer(userService.findByUsername(taskDTO.getPerformer()))
                .client(clientService.findByClientName(taskDTO.getClient()))
                .description(taskDTO.getDescription())
                .build();
    }

    public static TaskDTO toDto (Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setCreator(task.getCreator().getUsername());
        taskDTO.setPerformer(task.getPerformer().getUsername());
        taskDTO.setClient(task.getClient().getClientName());
        taskDTO.setDescription(task.getDescription());
        return taskDTO;
    }

    public static List<TaskDTO> mapFromTaskDTOListFromTasks(List<Task> taskList) {  //// уточнить и разобраться, возможно, переделать
        if (taskList == null || taskList.isEmpty()) {
            return null;
        } else {
            return taskList.stream()
                    .map(TaskMapper::toDto)
                    .collect(Collectors.toList());
        }
    }



}
