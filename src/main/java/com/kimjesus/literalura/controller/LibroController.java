package com.kimjesus.literalura.controller;

import com.kimjesus.literalura.dto.BusquedaLibroRequestDTO;
import com.kimjesus.literalura.dto.ErrorResponseDTO;
import com.kimjesus.literalura.dto.EstadisticasIdiomaDTO;
import com.kimjesus.literalura.dto.LibroDTO;
import com.kimjesus.literalura.service.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/libros")
@Tag(name = "Controlador de Libros", description = "Operaciones relacionadas con la gestión y búsqueda de libros")
public class LibroController {

    private final LibroService libroService;
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @Operation(summary = "Obtener todos los libros", description = "Devuelve una lista de todos los libros almacenados en la base de datos.")
    @GetMapping
    public List<LibroDTO> getAllLibros() {
        return libroService.obtenerTodosLosLibros();
    }

    @Operation(summary = "Buscar y guardar un libro", description = "Busca un libro por título en la API externa de Gutendex y lo guarda en la base de datos si se encuentra.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Libro encontrado y guardado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado en la API externa")
    })
    @PostMapping("/buscar")
    public ResponseEntity<Map<String, Object>> buscarYGuardar(@Valid @RequestBody BusquedaLibroRequestDTO request) {
        LibroDTO guardado = libroService.buscarYGuardarPorTitulo(request.titulo());
        Map<String, Object> response = Map.of(
            "mensaje", "✅ Libro guardado",
            "libro", guardado
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener los 10 libros más descargados", description = "Devuelve una lista del top 10 de libros con el mayor número de descargas.")
    @GetMapping("/top10")
    public List<LibroDTO> getTop10Libros() {
        return libroService.obtenerTop10Libros();
    }

    @Operation(summary = "Obtener libros por idioma", description = "Devuelve una lista de libros filtrada por un código de idioma específico (ej. 'es', 'en').")
    @GetMapping("/idioma")
    public List<LibroDTO> getLibrosPorIdioma(@RequestParam String idioma) {
        return libroService.obtenerLibrosPorIdioma(idioma);
    }

    @Operation(summary = "Obtener todos los idiomas disponibles", description = "Devuelve una lista de todos los códigos de idioma únicos de los libros en la base de datos.")
    @GetMapping("/idiomas")
    public List<String> getIdiomas() {
        return libroService.obtenerIdiomas();
    }

    @Operation(summary = "Obtener estadísticas por idioma", description = "Devuelve una lista con el conteo de libros por cada idioma.")
    @GetMapping("/estadisticas")
    public List<EstadisticasIdiomaDTO> getEstadisticas() {
        return libroService.obtenerEstadisticasPorIdioma();
    }
}
