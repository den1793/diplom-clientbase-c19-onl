package by.clientbase.diplomclientbasec19onl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author Denis Smirnov on 14.06.2023
 */

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String mainPage() {
        return "main";
    }



}

