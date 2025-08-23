package com.kimjesus.literalura.controller;

import com.kimjesus.literalura.model.Autor;
import com.kimjesus.literalura.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/autores")
@CrossOrigin
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping("/vivos")
    public List<Autor> getAutoresVivos(@RequestParam("anio") int anio) {
        return autorService.obtenerAutoresVivosEnAnio(anio);
    }
}
