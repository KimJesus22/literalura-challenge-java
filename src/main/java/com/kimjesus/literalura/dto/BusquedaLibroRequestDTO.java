package com.kimjesus.literalura.dto;

import jakarta.validation.constraints.NotBlank;

public record BusquedaLibroRequestDTO(
    @NotBlank(message = "El título no puede estar vacío")
    String titulo
) {
}
