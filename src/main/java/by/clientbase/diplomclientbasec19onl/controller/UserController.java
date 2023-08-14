package by.clientbase.diplomclientbasec19onl.controller;

import by.clientbase.diplomclientbasec19onl.dto.UserAuthorizationDTO;
import by.clientbase.diplomclientbasec19onl.dto.UserRegistrationDTO;
import by.clientbase.diplomclientbasec19onl.entity.SessionUser;
import by.clientbase.diplomclientbasec19onl.entity.User;

import by.clientbase.diplomclientbasec19onl.mapper.UserMapper;
import by.clientbase.diplomclientbasec19onl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Denis Smirnov on 14.06.2023
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;





    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("newUser", new UserRegistrationDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String reg(@ModelAttribute("newUser") @Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        boolean isRegistered = userService.save(userRegistrationDTO);
        if (isRegistered) {
            model.addAttribute("message", "User with this username is already exist");
            model.addAttribute("newUser", userRegistrationDTO);
            return "registration";
        } else {
            userService.save(userRegistrationDTO);
            return "redirect:/user/authorization";
        }
    }

    @GetMapping("/authorization")
    public String auth(Model model) {
        model.addAttribute("authUser", new UserAuthorizationDTO());
        return "authorization";
    }

    @PostMapping("/authorization")
    public String auth(
            Model model,
            HttpSession httpSession, UserAuthorizationDTO authorizationDTO) {

        User authenticatedUser = userService.findByUsername(authorizationDTO.getUsername());

        if (authenticatedUser!=null) {

            SessionUser sessionUser = UserMapper.toSessionUser(authenticatedUser);
            httpSession.setAttribute("user", sessionUser);

            return "redirect:/";

        } else {
            model.addAttribute("message", "User with this username doesn't exist");
            model.addAttribute("authUser", authorizationDTO);
            return "authorization";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "main";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") long id, Model model) {
        Optional<User> optionalUser = userService.findUserById(id);
        User user = optionalUser.orElse(null);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/all-users")
    public String showAllUsers(Model model, HttpSession httpSession) {
        model.addAttribute("users", userService.findAll());
        return "/all-users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }



    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "/user";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid UserRegistrationDTO userRegistrationDTO,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "/user";
        }
        userService.update(userRegistrationDTO.convertToUserUser(), id);
        return "/user";
    }



}