package com.example.spring_duoc.repository;

import com.example.spring_duoc.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
