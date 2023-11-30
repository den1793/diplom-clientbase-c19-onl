package by.clientbase.diplomclientbasec19onl.mapper;

import by.clientbase.diplomclientbasec19onl.dto.TaskDTO;
import by.clientbase.diplomclientbasec19onl.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDTO taskToDto(Task task);

    Task dtoToTask(TaskDTO taskDTO);

    Task updateTaskDtoToTask(TaskDTO taskDTO, long id);
}
