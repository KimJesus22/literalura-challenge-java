package com.kimjesus.literalura.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimjesus.literalura.dto.AutorDTO;
import com.kimjesus.literalura.dto.LibroDTO;
import com.kimjesus.literalura.service.LibroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@WebMvcTest(LibroController.class)
public class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService libroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllLibros_ShouldReturnListOfBooks() throws Exception {
        // Given
        AutorDTO autorDTO = new AutorDTO("Tolkien, J. R. R.", 1892, 1973);
        LibroDTO libroDTO = new LibroDTO(1L, "El Señor de los Anillos", "es", 1500, autorDTO);
        List<LibroDTO> todosLosLibros = Collections.singletonList(libroDTO);

        when(libroService.obtenerTodosLosLibros()).thenReturn(todosLosLibros);

        // When & Then
        mockMvc.perform(get("/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titulo", is("El Señor de los Anillos")))
                .andExpect(jsonPath("$[0].autor.nombre", is("Tolkien, J. R. R.")));
    }

    @Test
    public void testGetTop10Libros_ShouldReturnTop10() throws Exception {
        // Given
        AutorDTO autorDTO = new AutorDTO("Tolkien, J. R. R.", 1892, 1973);
        LibroDTO libroDTO = new LibroDTO(1L, "El Hobbit", "en", 2500, autorDTO);
        List<LibroDTO> top10Libros = Collections.singletonList(libroDTO);

        when(libroService.obtenerTop10Libros()).thenReturn(top10Libros);

        // When & Then
        mockMvc.perform(get("/libros/top10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titulo", is("El Hobbit")));
    }
}
