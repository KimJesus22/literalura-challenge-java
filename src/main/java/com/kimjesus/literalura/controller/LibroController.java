package com.kimjesus.literalura.controller;

import com.kimjesus.literalura.model.Libro;
import com.kimjesus.literalura.service.LibroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/buscar")
    public String buscarYGuardarLibro(@RequestParam("titulo") String titulo) {
        try {
            Libro libro = libroService.buscarYGuardarPorTitulo(titulo);
            return String.format("📘 Libro guardado: %s (%s)", libro.getTitulo(), libro.getAutor().getNombre());
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }
}
