package by.clientbase.diplomclientbasec19onl.mapper;

import by.clientbase.diplomclientbasec19onl.dto.UserRegistrationDTO;
import by.clientbase.diplomclientbasec19onl.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserRegistrationDTO userToDto(User user);

    User dtoToUser(UserRegistrationDTO registrationDTO);

    User updateUserDtoToUser (UserRegistrationDTO registrationDTO, long id);
}


