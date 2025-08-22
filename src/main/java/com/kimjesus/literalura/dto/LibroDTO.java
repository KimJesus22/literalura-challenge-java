package com.kimjesus.literalura.dto;

import com.kimjesus.literalura.model.Libro;

public record LibroDTO(
    Long id,
    String titulo,
    String idioma,
    int numeroDeDescargas,
    AutorDTO autor
) {
    public LibroDTO(Libro libro) {
        this(libro.getId(), libro.getTitulo(), libro.getIdioma(), libro.getNumeroDeDescargas(), new AutorDTO(libro.getAutor()));
    }
}
