// Ruta: src/main/java/com/example/spring_duoc/service/TokenStore.java

package com.example.spring_duoc.service;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TokenStore {

    private static final Logger logger = LoggerFactory.getLogger(TokenStore.class);

    private String token;

    /**
     * Método para obtener el token JWT almacenado.
     * 
     * @return token JWT.
     */
    public String getToken() {
        logger.info("Recuperando token JWT.");
        return token;
    }

    /**
     * Método para almacenar el token JWT recibido.
     * 
     * @param token El token JWT a almacenar.
     */
    public void setToken(String token) {
        this.token = token;
        logger.info("Token JWT almacenado correctamente.");
    }

    /**
     * Método para limpiar el token JWT almacenado.
     */
    public void clearToken() {
        this.token = null;
        logger.info("Token JWT eliminado del almacenamiento.");
    }
}
