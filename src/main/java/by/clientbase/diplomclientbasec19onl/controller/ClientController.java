package by.clientbase.diplomclientbasec19onl.controller;
import by.clientbase.diplomclientbasec19onl.dto.ClientDTO;
import by.clientbase.diplomclientbasec19onl.entity.Client;
import by.clientbase.diplomclientbasec19onl.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

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
            return "redirect:/";
        }
    }

    @GetMapping("/all-clients")
    public String showAllClients(Model model, HttpSession httpSession) {
        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "/client/all-clients";
    }

    /*@GetMapping("/all-clients")
    public String showAllClients(Model model, HttpSession httpSession) {
        model.addAttribute("clients", clientService.findAll());
        return "/client/all-clients";
    }*/
}
