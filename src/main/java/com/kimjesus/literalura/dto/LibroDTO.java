package com.kimjesus.literalura.dto;

import com.kimjesus.literalura.model.Libro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LibroDTO(
    Long id,
    @NotBlank(message = "El título no puede estar vacío")
    String titulo,
    @NotBlank(message = "El idioma no puede estar vacío")
    String idioma,
    @Min(value = 0, message = "El número de descargas no puede ser negativo")
    int numeroDeDescargas,
    @NotNull(message = "El autor no puede ser nulo")
    @Valid
    AutorDTO autor
) {
    public LibroDTO(Libro libro) {
        this(libro.getId(), libro.getTitulo(), libro.getIdioma(), libro.getNumeroDeDescargas(), new AutorDTO(libro.getAutor()));
    }
}
