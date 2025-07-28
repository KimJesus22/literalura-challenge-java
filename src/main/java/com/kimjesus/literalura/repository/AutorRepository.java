package com.kimjesus.literalura.repository;

import com.kimjesus.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Buscar autores vivos en un año determinado
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :anio AND (a.fechaFallecimiento IS NULL OR a.fechaFallecimiento > :anio)")
    List<Autor> findAutoresVivosEn(int anio);
    List<Autor> findByNombreContainingIgnoreCase(String nombre);
}
