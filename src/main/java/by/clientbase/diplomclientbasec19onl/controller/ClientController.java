package by.clientbase.diplomclientbasec19onl.controller;

import by.clientbase.diplomclientbasec19onl.dto.ClientDTO;
import by.clientbase.diplomclientbasec19onl.entity.Client;

import by.clientbase.diplomclientbasec19onl.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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
    public String addClient(@ModelAttribute("newClient") @Valid ClientDTO clientDTO,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/client/create";
        } else {
            boolean isClientExist = clientService.save(clientDTO);
            if (isClientExist) {
                return "redirect:/client/all-clients";
            } else {
                model.addAttribute("message", "Client with this name is already exist");
                model.addAttribute("newClient", clientDTO);
                return "/client/create";
            }
        }
    }

    @GetMapping("/all-clients")
    public String showAllClients(Model model,
                                 @RequestParam(required = false) String clientName,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "15") int size) {

        Page<Client> clientPage = clientService.findAllPaginated(clientName, page, size);
        List<Client> clientList = clientPage.getContent();
        model.addAttribute("clients", clientList);
        model.addAttribute("currentPage", clientPage.getNumber() + 1);
        model.addAttribute("totalItems", clientPage.getTotalElements());
        model.addAttribute("totalPages", clientPage.getTotalPages());
        model.addAttribute("pageSize", size);
        return "/client/all-clients";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("client", clientService.findById(id));
        return "client/client";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return "redirect:/client/all-clients";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("client", clientService.findById(id));
        return "/client/client";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("client") @Valid ClientDTO clientDTO,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "/client/client";
        }
        clientService.update(clientDTO, id);
        return "/client/client";
    }

}
