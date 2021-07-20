package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Curso;

import java.util.List;
import java.util.stream.Collectors;

public class CursoDto {

    private Long id;
    private String nome;
    private String descricao;
    private Integer cargaHoraria;

    public CursoDto(Curso curso) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.descricao = curso.getDescricao();
        this.cargaHoraria = curso.getCargaHoraria();
    }

    public static List<CursoDto> converter(List<Curso> allCursos) {
        return allCursos.stream().map(CursoDto::new).collect(Collectors.toList());
    }

    public static CursoDto converter(Curso curso) {
        return new CursoDto(curso);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }
}
