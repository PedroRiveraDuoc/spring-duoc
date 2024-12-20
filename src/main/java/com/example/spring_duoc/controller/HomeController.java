// Ruta: src/main/java/com/example/spring_duoc/controller/HomeController.java

package com.example.spring_duoc.controller;

import com.example.spring_duoc.service.TokenStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.security.core.Authentication;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final TokenStore tokenStore;

    public HomeController(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @GetMapping("/home")
    public String home(
            @RequestParam(name = "name", required = false, defaultValue = "Seguridad y Calidad en el Desarrollo") String name,
            Model model) {
        logger.info("Accediendo a la página de inicio.");
        model.addAttribute("name", name);
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        logger.info("Accediendo a la página de login.");
        return "login"; // Asegúrate de que este nombre coincide con el archivo login.html en templates
    }

    @GetMapping("/greetings")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "Juan González") String name,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }

        System.out.println("Nombre de usuario autenticado: " + auth.getName());
        String token = tokenStore.getToken();
        if (token == null) {
            System.out.println("Token JWT no encontrado en TokenStore");
            return "redirect:/login";
        }

        String url = "http://localhost:8080/greetings";
        final var restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("name", name);

        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
                String.class);
        System.out.println("Response: " + response);
        model.addAttribute("name", response.getBody());

        return "Greetings";
    }

}
