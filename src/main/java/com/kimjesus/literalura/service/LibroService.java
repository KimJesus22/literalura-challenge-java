package com.kimjesus.literalura.service;

import com.kimjesus.literalura.dto.EstadisticasIdiomaDTO;
import com.kimjesus.literalura.dto.LibroDTO;
import com.kimjesus.literalura.model.*;
import com.kimjesus.literalura.repository.AutorRepository;
import com.kimjesus.literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private static final String URL = "https://gutendex.com/books/?search=";

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public List<LibroDTO> obtenerTodosLosLibros() {
        return libroRepository.findAll().stream()
                .map(LibroDTO::new)
                .collect(Collectors.toList());
    }

    /** Busca por título en Gutendex, guarda el primer resultado y lo devuelve como DTO. */
    @Transactional
    public LibroDTO buscarYGuardarPorTitulo(String tituloBuscado) {
        if (libroRepository.existsByTituloIgnoreCase(tituloBuscado)) {
            throw new IllegalArgumentException("El libro ya está registrado.");
        }
        
        var q = URLEncoder.encode(tituloBuscado, StandardCharsets.UTF_8);
        var json = consumoApi.obtenerDatos(URL + q);
        var datos = conversor.obtenerDatos(json, Datos.class);

        if (datos.resultados() == null || datos.resultados().isEmpty()) {
            throw new IllegalArgumentException("No se encontró ningún libro con ese título.");
        }

        var d = datos.resultados().get(0); // primer resultado

        // Autor (tomamos el primero)
        var dAutor = d.autores().isEmpty() ? null : d.autores().get(0);
        Autor autor = null;
        if (dAutor != null) {
            autor = autorRepository
                    .findByNombreIgnoreCase(dAutor.nombre())
                    .orElseGet(() -> {
                        var a = new Autor();
                        a.setNombre(dAutor.nombre());
                        a.setFechaNacimiento(dAutor.fechaNacimiento());
                        a.setFechaFallecimiento(dAutor.fechaFallecimiento());
                        return autorRepository.save(a);
                    });
        }

        // Idioma (tomamos el primero si hay)
        String idioma = (d.idiomas() != null && !d.idiomas().isEmpty()) ? d.idiomas().get(0) : "desconocido";

        var libro = new Libro();
        libro.setTitulo(d.titulo());
        libro.setIdioma(idioma);
        libro.setNumeroDeDescargas(d.numeroDeDescargas());
        libro.setAutor(autor);

        Libro libroGuardado = libroRepository.save(libro);
        return new LibroDTO(libroGuardado);
    }

    public List<LibroDTO> obtenerTop10Libros() {
        return libroRepository.findTop10ByOrderByNumeroDeDescargasDesc().stream()
                .map(LibroDTO::new)
                .collect(Collectors.toList());
    }

    public List<LibroDTO> obtenerLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma).stream()
                .map(LibroDTO::new)
                .collect(Collectors.toList());
    }

    public List<String> obtenerIdiomas() {
        return libroRepository.findIdiomas().stream()
                .filter(idioma -> !idioma.equalsIgnoreCase("hu"))
                .collect(Collectors.toList());
    }

    public List<EstadisticasIdiomaDTO> obtenerEstadisticasPorIdioma() {
        return libroRepository.countLibrosPorIdioma();
    }
}
