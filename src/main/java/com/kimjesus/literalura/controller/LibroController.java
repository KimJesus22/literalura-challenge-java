package com.kimjesus.literalura.controller;

import com.kimjesus.literalura.dto.LibroDTO;
import com.kimjesus.literalura.service.LibroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/libros")
@CrossOrigin // quítalo si sirves el HTML desde el mismo host
public class LibroController {

    private final LibroService libroService;
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<LibroDTO> getAllLibros() {
        return libroService.obtenerTodosLosLibros();
    }

    @PostMapping("/buscar")
    public Map<String, Object> buscarYGuardar(@RequestParam String titulo) {
        try {
            LibroDTO guardado = libroService.buscarYGuardarPorTitulo(titulo);
            return Map.of(
                "mensaje", "✅ Libro guardado",
                "libro", guardado
            );
        } catch (IllegalArgumentException e) {
            return Map.of("error", e.getMessage());
        }
    }
}
