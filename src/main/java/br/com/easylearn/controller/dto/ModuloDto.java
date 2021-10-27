package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Modulo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuloDto {

    private Long id;
    private Integer indice;
    private String titulo;
    private String tituloSecundario;

    public ModuloDto(Modulo modulo) {
        this.id = modulo.getId();
        this.indice = modulo.getIndice();
        this.titulo = modulo.getTitulo();
        this.tituloSecundario = modulo.getTituloSecundario();
    }

    public static List<ModuloDto> converter(List<Modulo> allModulos) {
        return allModulos.stream().map(ModuloDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public Integer getIndice() {
        return indice;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTituloSecundario() {
        return tituloSecundario;
    }
}
