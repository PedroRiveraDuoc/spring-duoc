// Ruta: src/main/java/com/example/spring_duoc/config/CustomAuthenticationProvider.java

package com.example.spring_duoc.config;

import com.example.spring_duoc.model.AppUser;
import com.example.spring_duoc.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public CustomAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        logger.info("Iniciando autenticación para usuario: {}", username);

        AppUser user = userRepository.findByUsername(username);
        if (user == null) {
            logger.error("Usuario no encontrado: {}", username);
            throw new BadCredentialsException("Usuario no encontrado");
        }

        logger.info("Usuario encontrado. Comprobando contraseña para usuario: {}", username);
        logger.debug("Contraseña recibida: {}", rawPassword);
        logger.debug("Contraseña en base de datos (hash): {}", user.getPassword());

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            logger.error("Contraseña incorrecta para usuario: {}", username);
            throw new BadCredentialsException("Credenciales inválidas");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        logger.info("Autenticación exitosa para usuario: {}", username);
        return new UsernamePasswordAuthenticationToken(username, rawPassword, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
