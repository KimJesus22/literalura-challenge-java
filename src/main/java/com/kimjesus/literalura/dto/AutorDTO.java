package com.kimjesus.literalura.dto;

import com.kimjesus.literalura.model.Autor;

public record AutorDTO(
    String nombre,
    Integer fechaNacimiento,
    Integer fechaFallecimiento
) {
    public AutorDTO(Autor autor) {
        this(autor.getNombre(), autor.getFechaNacimiento(), autor.getFechaFallecimiento());
    }
}
