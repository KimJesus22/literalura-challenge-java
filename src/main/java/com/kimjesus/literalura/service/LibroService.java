package com.kimjesus.literalura.service;

import com.kimjesus.literalura.model.Autor;
import com.kimjesus.literalura.model.DatosLibros;
import java.util.Comparator;
import com.kimjesus.literalura.model.Libro;
import com.kimjesus.literalura.repository.LibroRepository;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class LibroService {
    private final LibroRepository repository;

    public LibroService(LibroRepository repository) {
        this.repository = repository;
    }

    public void registrarLibro(DatosLibros datos) {
        Libro libro = new Libro(datos);
        repository.save(libro);
        System.out.println("✅ Libro guardado: " + libro);
    }

    public List<Libro> listarLibros() {
        return repository.findAll();
    }
    public List<Libro> buscarLibrosPorAutor(Autor autor) {
        return repository.findByAutor(autor);
    }
    public long contarLibrosPorIdioma(String idioma) {
        return repository.countByIdioma(idioma);
    }

    public void mostrarEstadisticasDeDescargas() {
        var libros = repository.findAll();

        if (libros.isEmpty()) {
            System.out.println("⚠ No hay libros registrados en la base de datos.");
            return;
        }

        DoubleSummaryStatistics estadisticas = libros.stream()
                .collect(Collectors.summarizingDouble(Libro::getNumeroDeDescargas));

        System.out.println("\n📊 Estadísticas de descargas:");
        System.out.println("➡ Total de libros: " + estadisticas.getCount());
        System.out.println("📈 Máximo de descargas: " + estadisticas.getMax());
        System.out.println("📉 Mínimo de descargas: " + estadisticas.getMin());
        System.out.println("🔢 Promedio de descargas: " + estadisticas.getAverage());
    }
    public void mostrarTop10LibrosMasDescargados() {
        List<Libro> top10 = repository.findAll().stream()
                .sorted(Comparator.comparingInt(Libro::getNumeroDeDescargas).reversed())
                .limit(10)
                .toList();

        if (top10.isEmpty()) {
            System.out.println("⚠ No hay libros registrados para mostrar el Top 10.");
            return;
        }

        System.out.println("\n📚 Top 10 libros más descargados:");
        int posicion = 1;
        for (Libro libro : top10) {
            System.out.printf("%d. %s (%d descargas)%n", posicion++, libro.getTitulo(), libro.getNumeroDeDescargas());
        }
    }
}


