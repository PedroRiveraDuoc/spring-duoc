package com.example.spring_duoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/home")
    public String home(
            @RequestParam(name = "name", required = false, defaultValue = "Seguridad y Calidad en el Desarrollo") String name,
            Model model) {
        model.addAttribute("name", name);
        return "home";
    }

    @GetMapping("/")
    public String root(
            @RequestParam(name = "name", required = false, defaultValue = "Seguridad y Calidad en el Desarrollo") String name,
            Model model) {
        model.addAttribute("name", name);
        return "home";
    }

    // Añadir el método login aquí
    @GetMapping("/login")
    public String login() {
        logger.info("Entrando a la página de login");
        return "login"; // Devuelve la plantilla `login.html`
    }
}
