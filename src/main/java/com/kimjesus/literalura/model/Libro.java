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
    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }
    @Override
    public String toString() {
        return "📘 Libro: " + titulo +
                ", Idioma: " + idioma +
                ", Descargas: " + numeroDeDescargas +
                ", Autor: " + autor.getNombre();
    }
    public String getTitulo() {
        return titulo;
    }
    public String getIdioma() {
        return idioma;
    }

    public Autor getAutor() {
        return autor;
    }
}
