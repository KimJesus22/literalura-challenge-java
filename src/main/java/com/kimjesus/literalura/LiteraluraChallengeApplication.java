package com.kimjesus.literalura;

import com.kimjesus.literalura.model.Autor;
import com.kimjesus.literalura.model.Datos;
import com.kimjesus.literalura.model.Libro;
import com.kimjesus.literalura.repository.AutorRepository;
import com.kimjesus.literalura.repository.LibroRepository;
import com.kimjesus.literalura.service.AutorService;
import com.kimjesus.literalura.service.ConsumoApi;
import com.kimjesus.literalura.service.ConvierteDatos;
import com.kimjesus.literalura.service.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraluraChallengeApplication implements CommandLineRunner {

    private final LibroService libroService;
    private final AutorService autorService;

    public LiteraluraChallengeApplication(LibroRepository libroRepo, AutorRepository autorRepo) {
        this.libroService = new LibroService(libroRepo);
        this.autorService = new AutorService(autorRepo);
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner teclado = new Scanner(System.in);
        ConsumoApi consumoApi = new ConsumoApi();
        ConvierteDatos conversor = new ConvierteDatos();
        String URL = "https://gutendex.com/books/?search=";
        int opcion;

        do {
            System.out.println("""
                    \n==== MENÚ PRINCIPAL ====
                    1 - Buscar libro por título
                    2 - Ver historial de libros guardados
                    3 - Listar autores
                    4 - Buscar autores vivos en un año
                    5 - Ver cantidad de libros por idioma
                    6 - Mostrar estadísticas de descargas
                    7 - Mostrar Top 10 libros más descargados
                    8 - Buscar autor por nombre
                    0 - Salir
                    Seleccione una opción: """);
            opcion = teclado.nextInt();
            teclado.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("🔍 Ingrese el título del libro a buscar: ");
                    var tituloLibro = teclado.nextLine();
                    var tituloCodificado = URLEncoder.encode(tituloLibro, StandardCharsets.UTF_8);
                    var json = consumoApi.obtenerDatos(URL + tituloCodificado);
                    var datos = conversor.obtenerDatos(json, Datos.class);

                    if (datos.resultados().isEmpty()) {
                        System.out.println("❌ No se encontró ningún libro con ese título.");
                    } else {
                        datos.resultados().stream()
                                .limit(1)
                                .forEach(libroService::registrarLibro);
                    }
                }
                case 2 -> {
                    List<Libro> libros = libroService.listarLibros();
                    if (libros.isEmpty()) {
                        System.out.println("📭 Aún no hay libros guardados.");
                    } else {
                        libros.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    autorService.listarAutores().forEach(autor ->
                            System.out.println("👤 Autor: " + autor.getNombre()
                                    + ", Nacimiento: " + autor.getFechaNacimiento()
                                    + ", Fallecimiento: " + autor.getFechaFallecimiento()));
                }
                case 4 -> {
                    try {
                        System.out.print("📅 Ingrese el año para buscar autores vivos: ");
                        int anio = Integer.parseInt(teclado.nextLine());

                        List<Autor> autoresVivos = autorService.buscarAutoresVivosEn(anio);
                        if (autoresVivos.isEmpty()) {
                            System.out.println("❌ No se encontraron autores vivos en ese año.");
                        } else {
                            autoresVivos.forEach(autor ->
                                    System.out.println("🟢 Vivo en " + anio + ": " + autor.getNombre()));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Debe ingresar un año válido (número).");
                    }
                }
                case 5 -> mostrarCantidadDeLibrosPorIdioma(teclado);
                case 6 -> libroService.mostrarEstadisticasDeDescargas();
                case 7 -> libroService.mostrarTop10LibrosMasDescargados();
                case 8 -> {
                    System.out.print("🔍 Ingrese el nombre (o parte del nombre) del autor a buscar: ");
                    String nombreAutor = teclado.nextLine();

                    List<Autor> autores = autorService.buscarAutorPorNombre(nombreAutor);
                    if (autores.isEmpty()) {
                        System.out.println("❌ No se encontraron autores con ese nombre.");
                    } else {
                        for (Autor a : autores) {
                            System.out.println("📚 Autor: " + a.getNombre() +
                                    " | Nacimiento: " + a.getFechaNacimiento() +
                                    " | Fallecimiento: " + a.getFechaFallecimiento());

                            List<Libro> librosDelAutor = libroService.buscarLibrosPorAutor(a);
                            if (librosDelAutor.isEmpty()) {
                                System.out.println("   (Sin libros registrados)");
                            } else {
                                for (Libro libro : librosDelAutor) {
                                    System.out.println("   - 📘 " + libro.getTitulo());
                                }
                            }
                        }
                    }
                }
                case 0 -> System.out.println("👋 Saliendo de la aplicación...");
                default -> System.out.println("⚠️ Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private void mostrarCantidadDeLibrosPorIdioma(Scanner scanner) {
        System.out.println("🌍 Idiomas disponibles:");
        System.out.println("1 - Inglés (en)");
        System.out.println("2 - Español (es)");
        System.out.print("Seleccione un idioma: ");
        var opcionIdioma = scanner.nextLine();

        String idioma = switch (opcionIdioma) {
            case "1" -> "en";
            case "2" -> "es";
            default -> {
                System.out.println("⚠️ Idioma no válido.");
                yield null;
            }
        };

        if (idioma != null) {
            long cantidad = libroService.contarLibrosPorIdioma(idioma);
            System.out.println("📚 Cantidad de libros en " + idioma + ": " + cantidad);
        }
    }
}





