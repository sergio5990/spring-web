package by.academy.it.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping(value = "/")
    public String welcomePage() {
        return "redirect:/persons/page";
    }
}



