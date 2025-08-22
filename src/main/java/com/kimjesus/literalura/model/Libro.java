package com.kimjesus.literalura.model;

import jakarta.persistence.*;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private int numeroDeDescargas;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    public Libro() {}

    public Libro(DatosLibros datos) {
        this.titulo = datos.titulo();
        this.idioma = datos.idiomas().get(0);
        this.numeroDeDescargas = datos.numeroDeDescargas();
        this.autor = new Autor(datos.autores().get(0));
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setNumeroDeDescargas(int numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "📘 Libro: " + titulo +
                ", Idioma: " + idioma +
                ", Descargas: " + numeroDeDescargas +
                ", Autor: " + (autor != null ? autor.getNombre() : "Desconocido");
    }
}
