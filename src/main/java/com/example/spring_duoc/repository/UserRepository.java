// Ruta: src/main/java/com/example/spring_duoc/repository/UserRepository.java

package com.example.spring_duoc.repository;

import com.example.spring_duoc.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
