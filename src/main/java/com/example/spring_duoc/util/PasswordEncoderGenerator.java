package com.example.spring_duoc.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password123";  // Cambia esto a la contraseña que deseas encriptar
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Contraseña encriptada: " + encodedPassword);
    }
}
