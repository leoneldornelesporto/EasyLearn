package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Modulo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuloDto {

    private Integer indice;
    private String titulo;
    private String tituloSecundario;
    private List<AulaDto> aulaDto;
    private List<String> subtitulo;

    public ModuloDto(Modulo modulo) {
        this.indice = modulo.getIndice();
        this.titulo = modulo.getTitulo();
        this.tituloSecundario = modulo.getTituloSecundario();
        this.aulaDto = AulaDto.converter(modulo.getAulaList());
        this.subtitulo = modulo.getSubtitulo();
    }

    public static List<ModuloDto> converter(List<Modulo> allModulos) {
        return allModulos.stream().map(ModuloDto::new).collect(Collectors.toList());
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

    public List<AulaDto> getAulaDto() {
        return aulaDto;
    }

    public List<String> getSubtitulo() {
        return subtitulo;
    }
}
