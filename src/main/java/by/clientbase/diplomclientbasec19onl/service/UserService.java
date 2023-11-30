package by.clientbase.diplomclientbasec19onl.service;

import by.clientbase.diplomclientbasec19onl.dto.UserRegistrationDTO;
import by.clientbase.diplomclientbasec19onl.enums.Role;
import by.clientbase.diplomclientbasec19onl.entity.User;
import by.clientbase.diplomclientbasec19onl.mapper.UserMapper;

import by.clientbase.diplomclientbasec19onl.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    private final Logger log = Logger.getLogger(UserService.class.getName());

    public boolean save(UserRegistrationDTO userRegistrationDTO) {

        User user = UserMapper.INSTANCE.dtoToUser(userRegistrationDTO);

        user.setRoles(Set.of(Role.ADMIN));
        user.setPassword(passwordEncoder().encode(userRegistrationDTO.getPassword()));

        userRepository.save(user);
        log.log(Level.INFO, "User saved " + userRegistrationDTO.getUsername());
        return true;
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByUsername(String username) {

        return userRepository.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            return byUsername.get();
        }
        throw new UsernameNotFoundException("User not found");
    }

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public String getAuthenticationUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> findAll() {
        return userRepository.findAll(Sort.by("username").ascending());
    }

    public Page<User> findAllPaginated(String username, int page, int size) {
        return userRepository.findAllPaginated(username, PageRequest.of(page - 1, size, Sort.by("username").ascending()));
    }

    public boolean update(UserRegistrationDTO userRegistrationDTO, long id) {

        Optional<User> existUser = userRepository.findById(id);

        if (existUser.isPresent()) {
            User user = UserMapper.INSTANCE.updateUserDtoToUser(userRegistrationDTO, existUser.get().getId());
            user.setRoles(Set.of(Role.USER));
            user.setPassword(passwordEncoder().encode(userRegistrationDTO.getPassword()));
            userRepository.save(user);
            log.log(Level.INFO, "User is update " + userRegistrationDTO.getUsername());
            return true;
        } else {

            return false;
        }

    }

}
