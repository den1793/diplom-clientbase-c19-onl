package by.clientbase.diplomclientbasec19onl.mapper;

import by.clientbase.diplomclientbasec19onl.dto.UserRegistrationDTO;
import by.clientbase.diplomclientbasec19onl.entity.SessionUser;
import by.clientbase.diplomclientbasec19onl.entity.Telephone;
import by.clientbase.diplomclientbasec19onl.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Denis Smirnov on 15.06.2023
 */
public class UserMapper {
    public static SessionUser toSessionUser(User user) {
        SessionUser sessionUser = new SessionUser();
        sessionUser.setId(user.getId());
        sessionUser.setName(user.getName());
        sessionUser.setUsername(user.getUsername());
        return sessionUser;
    }

    public static User toUser(UserRegistrationDTO registrationDTO) {
        return User.builder()
                .name(registrationDTO.getName())
                .lastname(registrationDTO.getLastname())
                .username(registrationDTO.getUsername())
                .password(registrationDTO.getPassword())
                .email(registrationDTO.getPosition())
                .position(registrationDTO.getPosition())
                .telephones(List.of(Telephone.builder()
                        .number(registrationDTO.getTelephone())
                        .build()))
                .build();
    }

    public static UserRegistrationDTO toDto(User user){
        UserRegistrationDTO userDTO = new UserRegistrationDTO();
        userDTO.setUser_id(user.getId());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

//    public static List<UserRegistrationDTO> mapFromUserDTOListFromUsers(List<User> userList) {
//        if (userList == null || userList.isEmpty()) {
//            return null;
//        } else {
//            return userList.stream()
//                    .map(UserMapper::mapFromUserToUserDto)
//                    .collect(Collectors.toList());
//        }
//    }
}
