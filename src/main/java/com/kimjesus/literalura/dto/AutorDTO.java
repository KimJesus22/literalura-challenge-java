package com.kimjesus.literalura.dto;

import com.kimjesus.literalura.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutorDTO(
    @NotBlank(message = "El nombre del autor no puede estar vacío")
    String nombre,
    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    Integer fechaNacimiento,
    Integer fechaFallecimiento
) {
    public AutorDTO(Autor autor) {
        this(autor.getNombre(), autor.getFechaNacimiento(), autor.getFechaFallecimiento());
    }
}
