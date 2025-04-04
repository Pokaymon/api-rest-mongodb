package com.mundialista.api_rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("mensaje", "¡Bienvenido a Mundialista!");
        return "HomePage";  // Carga la vista HomePage.html en templates/
    }
}

