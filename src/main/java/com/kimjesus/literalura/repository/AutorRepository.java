package com.kimjesus.literalura.repository;

import com.kimjesus.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.fechaFallecimiento > :anio AND a.fechaNacimiento <= :anio")
    List<Autor> findAutoresVivosEnAnio(int anio);
}
