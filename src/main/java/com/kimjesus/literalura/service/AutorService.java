package com.kimjesus.literalura.service;

import com.kimjesus.literalura.model.Autor;
import com.kimjesus.literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> buscarAutoresVivosEn(int anio) {
        return autorRepository.findAutoresVivosEn(anio);
    }
    public List<Autor> buscarAutorPorNombre(String nombre) {
        return autorRepository.findByNombreContainingIgnoreCase(nombre);
    }
}

