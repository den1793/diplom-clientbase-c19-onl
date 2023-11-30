package by.clientbase.diplomclientbasec19onl.controller;

import by.clientbase.diplomclientbasec19onl.dto.ClientDTO;
import by.clientbase.diplomclientbasec19onl.dto.TaskDTO;
import by.clientbase.diplomclientbasec19onl.entity.*;
import by.clientbase.diplomclientbasec19onl.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AttachmentService attachmentService;

    @GetMapping("/create")
    public String addTask(Model model) {
        model.addAttribute("newTask", new TaskDTO());
        model.addAttribute("creator", userService.getCurrentUsername());
        model.addAttribute("performers", userService.findAll());
        model.addAttribute("clients", clientService.findAll());
        return "/task/create";
    }


    @PostMapping("/create")
    public String addTask(@ModelAttribute("newTask") @Valid TaskDTO taskDTO,
                          BindingResult bindingResult, @AuthenticationPrincipal User user,
                          Model model) {
        if (bindingResult.hasErrors()) {
            return "/task/create";
        } else {
            boolean isTaskExist = taskService.save(taskDTO);
            if (isTaskExist) {
                ;
                return "redirect:/task/all-tasks";
            } else {
                model.addAttribute("message", "Error message");
                model.addAttribute("newTask", taskDTO);
                return "/client/create";
            }
        }
    }

    @GetMapping("/all-tasks")
    public String showAllTasks(Model model,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "15") int size) {

        Page<Task> taskPage = taskService.findAllPaginated(page, size);
        List<Task> taskList = taskPage.getContent();
        model.addAttribute("tasks", taskList);
        model.addAttribute("currentPage", taskPage.getNumber() + 1);
        model.addAttribute("totalItems", taskPage.getTotalElements());
        model.addAttribute("totalPages", taskPage.getTotalPages());
        model.addAttribute("pageSize", size);
        return "/task/all-tasks";
    }

    @GetMapping("/my-tasks")
    public String showTasksByUser(Model model,
                                  @AuthenticationPrincipal User user,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "15") int size) {

        Page<Task> taskPage = taskService.findByUser(page, size);
        List<Task> taskByUser = taskPage.getContent();
        model.addAttribute("tasks", taskByUser);
        model.addAttribute("currentPage", taskPage.getNumber() + 1);
        model.addAttribute("totalItems", taskPage.getTotalElements());
        model.addAttribute("totalPages", taskPage.getTotalPages());
        model.addAttribute("pageSize", size);
        return "task/my-tasks";

    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") Long id, Model model) {
        Optional<Task> optionalTask = taskService.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            model.addAttribute("task", task);
            model.addAttribute("creator", userService.getCurrentUsername());
            model.addAttribute("performers", userService.findAll());
            model.addAttribute("clients", clientService.findAll());
        }
        return "task/task";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/task/all-tasks";
    }


    @PutMapping("/{id}")
    public String update(@ModelAttribute("task") @Valid TaskDTO taskDTO,
                         BindingResult bindingResult, @AuthenticationPrincipal User user, Model model,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "/task/task";
        }
        List<User> userList = userService.findAll();
        model.addAttribute("performers", userList);
        List<Client> clientList = clientService.findAll();
        model.addAttribute("clients", clientList);
        taskService.update(taskDTO, id);
        return "redirect:/task/{id}";
    }


    @PutMapping("/send/{id}")
    public String sendToMail(@ModelAttribute("email")
                             @Valid TaskDTO taskDTO,
                             @PathVariable("id") Long id, Model model) throws MessagingException {

        Optional<Task> optionalTask = taskService.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            Email email = new Email();
            email.setFrom(task.getCreator().getEmail());
            email.setTo(task.getPerformer().getEmail());
            email.setSubject(String.valueOf(task.getId()));
            email.setText(task.toString());
            emailService.sendMail(email);
            model.addAttribute("email", email);
        }
        return "redirect:/task/{id}";
    }

    @GetMapping("/{id}/upload")
    public String addAttachmentToTask(Model model, @PathVariable String id) {
        return "/task/task";
    }


    @PostMapping("/{id}/upload")
    public String addAttachmentToTask(@PathVariable Long id,
                                      @ModelAttribute("Attachment") Attachment attachment,
                                      @RequestParam("file") MultipartFile file) {

        Optional<Task> taskOptional = taskService.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();

            String fileName = file.getOriginalFilename();

            String uploadDir = "static/upload/";
            String filePath = uploadDir + fileName;

            try {
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Files.write(Paths.get(filePath), file.getBytes());

                attachment.setFileName(fileName);
                attachment.setUrl(filePath);
                attachment.setTask(task);

                attachmentService.save(attachment);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/task/{id}";
    }

    @GetMapping(path = "/{taskId}/download/{attachmentId:.+}")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable Long taskId,
                                                       @PathVariable Long attachmentId,
                                                       HttpServletResponse response) throws IOException {

        Optional<Task> taskOptional = taskService.findById(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            Optional<Attachment> attachmentOptional = task.getAttachments().stream()
                    .filter(attachment -> attachment.getId().equals(attachmentId))
                    .findFirst();
            if (attachmentOptional.isPresent()) {
                Attachment attachment = attachmentOptional.get();

                File file = new File(attachment.getUrl());
                Path path = Paths.get(file.getAbsolutePath());
                ByteArrayResource resource = new ByteArrayResource
                        (Files.readAllBytes(path));

                return ResponseEntity.ok().headers(this.headers(attachment.getUrl()))
                        .contentLength(file.length())
                        .contentType(MediaType.parseMediaType
                                ("application/octet-stream")).body(resource);
            }
        }
        return null;
    }

    private HttpHeaders headers(String name) {

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + name);
        header.add("Cache-Control", "no-cache, no-store,"
                + " must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return header;

    }
}
