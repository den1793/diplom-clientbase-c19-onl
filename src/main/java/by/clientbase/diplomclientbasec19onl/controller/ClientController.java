package by.clientbase.diplomclientbasec19onl.controller;
import by.clientbase.diplomclientbasec19onl.dto.ClientDTO;
import by.clientbase.diplomclientbasec19onl.dto.TaskDTO;
import by.clientbase.diplomclientbasec19onl.entity.Client;
import by.clientbase.diplomclientbasec19onl.entity.Task;
import by.clientbase.diplomclientbasec19onl.mapper.ClientMapper;
import by.clientbase.diplomclientbasec19onl.mapper.TaskMapper;
import by.clientbase.diplomclientbasec19onl.service.ClientService;
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
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/create")
    public String addClient(Model model) {
        model.addAttribute("newClient", new ClientDTO());
        return "/client/create";
    }

    @PostMapping("/create")
    public String addClient(@ModelAttribute("newClient") @Valid ClientDTO clientDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/client/create";
        }
        Client byClientName = clientService.findByClientName(clientDTO.getClientName());
        if (byClientName != null) {
            model.addAttribute("message", "Client with this name is already exist");
            model.addAttribute("newClient", clientDTO);
            return "/client/create";
        } else {
            clientService.save(clientDTO);
            return "redirect:/client/all-clients";
        }
    }

    @GetMapping("/all-clients")
    public String showAllClients(Model model, HttpSession httpSession) {
        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "/client/all-clients";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") long id, Model model) {
        Optional<Client> optionalClient = clientService.findById(id);
        Client client = optionalClient.orElse(null);
        model.addAttribute("client", client);
        return "client/client";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("client", clientService.findById(id));
        return "/client/client";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("client") @Valid ClientDTO clientDTO,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "/client/client";
        }

        clientService.update(ClientMapper.toClient(clientDTO), id);
        return "/client/client";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        clientService.deleteClientById(id);
        return "redirect:/client/all-clients";
    }
}
