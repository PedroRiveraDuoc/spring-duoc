// Ruta: src/main/java/com/example/spring_duoc/controller/HomeController.java

package com.example.spring_duoc.controller;

import com.example.spring_duoc.service.TokenStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
        logger.info("Accediendo al endpoint /greetings con nombre: {}", name);

        String url = "http://localhost:8080/greetings";
        String token = tokenStore.getToken();

        if (token == null || token.isEmpty()) {
            logger.warn("Token JWT no encontrado en TokenStore.");
            model.addAttribute("name", "Token no disponible. Por favor, inicie sesión.");
            return "greetings";
        }

        // Crear los encabezados de la solicitud y añadir el token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token); // Añadir "Bearer " antes del token
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        // Construir la URL con los parámetros
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("name", name);

        // Realizar la petición con el token en el encabezado y los parámetros en la URL
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
                    String.class);

            logger.info("Respuesta recibida del backend: {}", response.getBody());
            model.addAttribute("name", response.getBody());
        } catch (Exception ex) {
            logger.error("Error al realizar la solicitud al backend: {}", ex.getMessage());
            model.addAttribute("name", "Error al obtener saludo. Intente más tarde.");
        }

        return "greetings";
    }
}
