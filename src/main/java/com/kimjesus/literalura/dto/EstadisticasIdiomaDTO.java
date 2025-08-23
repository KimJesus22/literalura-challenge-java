package com.kimjesus.literalura.dto;

public class EstadisticasIdiomaDTO {
    private String idioma;
    private Long total;

    public EstadisticasIdiomaDTO(String idioma, Long total) {
        this.idioma = idioma;
        this.total = total;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
