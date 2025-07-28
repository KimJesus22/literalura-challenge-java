package com.kimjesus.literalura.model;

import jakarta.persistence.*;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;

    public Autor() {
    }

    public Autor(String nombre, Integer fechaNacimiento, Integer fechaFallecimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
    }

    // 🔧 Este constructor es el que necesitas para convertir desde DatosAutor
    public Autor(DatosAutor datos) {
        this.nombre = datos.nombre();
        this.fechaNacimiento = datos.fechaNacimiento();
        this.fechaFallecimiento = datos.fechaFallecimiento();
    }

    // Getters necesarios
    public String getNombre() {
        return nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }
}


