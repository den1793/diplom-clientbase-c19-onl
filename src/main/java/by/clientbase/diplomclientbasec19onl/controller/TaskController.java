package by.clientbase.diplomclientbasec19onl.controller;

import by.clientbase.diplomclientbasec19onl.dto.TaskDTO;
import by.clientbase.diplomclientbasec19onl.entity.Task;
import by.clientbase.diplomclientbasec19onl.entity.User;
import by.clientbase.diplomclientbasec19onl.service.ClientService;
import by.clientbase.diplomclientbasec19onl.service.TaskService;
import by.clientbase.diplomclientbasec19onl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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
//        Task task = taskService.save(taskDTO);

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

    @GetMapping("/all-tasks")
    public String showAllTasks(Model model, HttpSession httpSession) {
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "/task/all-tasks";
    }

    /*@GetMapping("/all-tasks")
    public String showAllTasks(Model model, HttpSession httpSession) {
        model.addAttribute("tasks", taskService.findAll());
        return "/task/all-tasks";
    }*/
}
