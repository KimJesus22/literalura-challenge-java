package com.kimjesus.literalura.service;

import com.kimjesus.literalura.model.Autor;
import com.kimjesus.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> obtenerAutoresVivosEnAnio(int anio) {
        return autorRepository.findAutoresVivosEnAnio(anio);
    }
}
