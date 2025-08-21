package com.kimjesus.literalura.repository;

import com.kimjesus.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.fechaFallecimiento > :anio AND a.fechaNacimiento <= :anio")
    List<Autor> findAutoresVivosEnAnio(int anio);

    // Para evitar duplicar autores al guardar:
    Optional<Autor> findByNombreIgnoreCase(String nombre);
}
