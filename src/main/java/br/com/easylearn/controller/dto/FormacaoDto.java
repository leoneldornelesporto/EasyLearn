package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Formacao;

import java.util.List;
import java.util.stream.Collectors;

public class FormacaoDto {

    private String titulo;
    private String descricao;
    private List<CursoDto> cursoDtoList;

    public FormacaoDto(Formacao formacao) {
        this.titulo = formacao.getTitulo();
        this.descricao = formacao.getDescricao();
        this.cursoDtoList = CursoDto.converter(formacao.getCursoList());
    }

    public static List<FormacaoDto> converter(List<Formacao> allFormacoes) {
        return allFormacoes.stream().map(FormacaoDto::new).collect(Collectors.toList());
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<CursoDto> getCursoDtoList() {
        return cursoDtoList;
    }
}
