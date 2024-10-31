// Ruta: src/main/java/com/example/spring_duoc/service/CustomUserDetailsService.java

package com.example.spring_duoc.service;

import com.example.spring_duoc.model.AppUser;
import com.example.spring_duoc.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Importación añadida
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        return new User(user.getUsername(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
    }
}
