package com.kimjesus.literalura.repository;

import com.kimjesus.literalura.model.Libro;
import com.kimjesus.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    long countByIdioma(String idioma);
    List<Libro> findByAutor(Autor autor);
}

