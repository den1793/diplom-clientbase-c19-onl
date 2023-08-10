package by.clientbase.diplomclientbasec19onl.service;

import by.clientbase.diplomclientbasec19onl.dto.UserAuthorizationDTO;
import by.clientbase.diplomclientbasec19onl.dto.UserRegistrationDTO;
import by.clientbase.diplomclientbasec19onl.entity.User;
//import by.clientbase.diplomclientbasec19onl.mapper.UserMapper;
//import by.clientbase.diplomclientbasec19onl.mapper.UserRegistrationMapper;
import by.clientbase.diplomclientbasec19onl.mapper.UserMapper;
import by.clientbase.diplomclientbasec19onl.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Denis Smirnov on 14.06.2023
 */
@Service
public class UserService {
    //    @Autowired
    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean save(UserRegistrationDTO userRegistrationDTO) {
        User user = UserMapper.toUser(userRegistrationDTO);
        userRepository.save(user);
        return true;
    }

    public void deleteUserById(long id) { userRepository.deleteById(id);}

    public User findByUsername(String username) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        if (user.isPresent()) {
            return user.get();
        }else {
            throw new UsernameNotFoundException("User with this username doesn't exist!");
        }

    }



    public User login(UserAuthorizationDTO authorizationDTO) {
        return userRepository.findByUsername(authorizationDTO.getUsername());
    }

    public String getAuthenticationUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    public User getCurrentUser(){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

//    public List<UserRegistrationDTO> findAllUserList() {
//        List<UserRegistrationDTO> userDTOList = UserMapper.mapFromUserDTOListFromUsers(userRepository.findAll());
//        return userDTOList;
//    }


}
