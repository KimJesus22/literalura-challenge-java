package com.kimjesus.literalura.controller;

import com.kimjesus.literalura.dto.BusquedaLibroRequestDTO;
import com.kimjesus.literalura.dto.EstadisticasIdiomaDTO;
import com.kimjesus.literalura.dto.LibroDTO;
import com.kimjesus.literalura.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/libros")
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
    public ResponseEntity<Map<String, Object>> buscarYGuardar(@Valid @RequestBody BusquedaLibroRequestDTO request) {
        LibroDTO guardado = libroService.buscarYGuardarPorTitulo(request.titulo());
        Map<String, Object> response = Map.of(
            "mensaje", "✅ Libro guardado",
            "libro", guardado
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/top10")
    public List<LibroDTO> getTop10Libros() {
        return libroService.obtenerTop10Libros();
    }

    @GetMapping("/idioma")
    public List<LibroDTO> getLibrosPorIdioma(@RequestParam String idioma) {
        return libroService.obtenerLibrosPorIdioma(idioma);
    }

    @GetMapping("/idiomas")
    public List<String> getIdiomas() {
        return libroService.obtenerIdiomas();
    }

    @GetMapping("/estadisticas")
    public List<EstadisticasIdiomaDTO> getEstadisticas() {
        return libroService.obtenerEstadisticasPorIdioma();
    }
}
