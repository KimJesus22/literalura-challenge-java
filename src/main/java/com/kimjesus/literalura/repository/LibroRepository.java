package com.kimjesus.literalura.repository;

import com.kimjesus.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByTituloIgnoreCase(String titulo);

    List<Libro> findTop10ByOrderByNumeroDeDescargasDesc();

    List<Libro> findByIdioma(String idioma);

    @Query("SELECT DISTINCT l.idioma FROM Libro l")
    List<String> findIdiomas();
}
