package by.clientbase.diplomclientbasec19onl.controller;

import by.clientbase.diplomclientbasec19onl.dto.TaskDTO;
import by.clientbase.diplomclientbasec19onl.dto.UserRegistrationDTO;
import by.clientbase.diplomclientbasec19onl.entity.Task;
import by.clientbase.diplomclientbasec19onl.entity.User;
import by.clientbase.diplomclientbasec19onl.mapper.TaskMapper;
import by.clientbase.diplomclientbasec19onl.service.ClientService;
import by.clientbase.diplomclientbasec19onl.service.TaskService;
import by.clientbase.diplomclientbasec19onl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Denis Smirnov on 14.06.2023
 */
@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClientService clientService;


    @GetMapping("/create")
    public String addTask(Model model) {
        model.addAttribute("newTask", new TaskDTO());
        model.addAttribute("creator", userService.findAll());
        model.addAttribute("performer", userService.findAll());
        model.addAttribute("client", clientService.findAll());
        return "/task/create";
    }


    @PostMapping("/create") // доработать, сделать маппер
    public String addTask(@ModelAttribute("newTask") TaskDTO taskDTO, Model model) {
        taskService.save(taskDTO);
        return "redirect:/";
    }

   /* @PostMapping("/create")
    public String addTask(@ModelAttribute("newTask") TaskDTO taskDTO, Model model) {
        boolean isRegistered = taskService.save(taskDTO);
        if (!isRegistered) {
            model.addAttribute("message", "Task with this username is already exist");
            model.addAttribute("newTask", taskDTO);
            return "/task/create";
        } else {
            taskService.save(taskDTO);
            return "redirect:/";
        }
    }*/

    /*@GetMapping("/all-tasks")
    public String showAllTasks(Model model, HttpSession httpSession) {
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "/task/all-tasks";
    }*/

    @GetMapping("/all-tasks")
    public String showAllTasks(Model model, HttpSession httpSession) {
        model.addAttribute("tasks", taskService.findAll());
        return "task/all-tasks";
    }
    @GetMapping("/{id}")
    public String showById(@PathVariable("id") long id, Model model) {
        Optional<Task> optionalTask = taskService.findById(id);
        Task task = optionalTask.orElse(null);
        model.addAttribute("task", task);
        return "task/task";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("task", taskService.findById(id));
        return "/task/task";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("task") @Valid TaskDTO taskDTO,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "/task/task";
        }

       taskService.update(TaskMapper.toTask(taskDTO), id);
        return "/task/task";
    }

}
