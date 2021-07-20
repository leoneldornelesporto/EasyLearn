package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Modulo;

import java.util.List;
import java.util.stream.Collectors;

public class ModuloDto {

    private Integer indice;
    private String titulo;
    private CursoDto cursoDto;
    private AulaDto aulaDto;

    public ModuloDto(Modulo modulo) {
        this.indice = modulo.getIndice();
        this.titulo = modulo.getTitulo();
        this.cursoDto = CursoDto.converter(modulo.getCurso());
        this.aulaDto = AulaDto.converter(modulo.getAula());
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

    public CursoDto getCursoDto() {
        return cursoDto;
    }

    public AulaDto getAulaDto() {
        return aulaDto;
    }
}
