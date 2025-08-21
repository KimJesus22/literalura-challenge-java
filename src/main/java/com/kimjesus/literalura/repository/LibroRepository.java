package com.kimjesus.literalura.repository;

import com.kimjesus.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByTituloIgnoreCase(String titulo);
}
