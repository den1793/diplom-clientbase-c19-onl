package by.clientbase.diplomclientbasec19onl.controller;

import by.clientbase.diplomclientbasec19onl.dto.UserAuthorizationDTO;
import by.clientbase.diplomclientbasec19onl.dto.UserRegistrationDTO;

import by.clientbase.diplomclientbasec19onl.entity.User;

import by.clientbase.diplomclientbasec19onl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


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
    public String registration(@ModelAttribute("newUser") @Valid UserRegistrationDTO userRegistrationDTO,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            boolean isRegistered = userService.save(userRegistrationDTO);
            if (isRegistered) {
                return "redirect:/";
            } else {
                model.addAttribute("message", "User with this username is already exist");
                model.addAttribute("newUser", userRegistrationDTO);
                return "registration";
            }
        }
    }

    @GetMapping("/authorization")
    public String auth(Model model) {
        model.addAttribute("authUser", new UserAuthorizationDTO());
        return "authorization";
    }

    @PostMapping("/authorization")
    public String auth(
            @ModelAttribute("authorization") @Valid UserAuthorizationDTO userAuthorizationDTO,
            BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authorization", userAuthorizationDTO);
            return "authorization";
        }

        Optional<User> byUsername = userService.findByUsername(userAuthorizationDTO.getUsername());

        if (byUsername.isPresent()) {
            session.setAttribute("authUser", byUsername.get());
            return "redirect:/";
        } else {
            model.addAttribute("errorLogin", "Wrong username or password");
            return "authorization";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "user";
    }


    @GetMapping("/all-users")
    public String showAllUsers(Model model,
                               @RequestParam(required = false) String username,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "15") int size) {

        Page<User> userPage = userService.findAllPaginated(username, page, size);
        List<User> userList = userPage.getContent();
        model.addAttribute("users", userList);
        model.addAttribute("currentPage", userPage.getNumber() + 1);
        model.addAttribute("totalItems", userPage.getTotalElements());
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("pageSize", size);
        return "/all-users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/user/all-users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "/user";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid UserRegistrationDTO userRegistrationDTO,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "/user";
        }
        userService.update(userRegistrationDTO, id);
        return "redirect:/user/{id}";
    }

}