package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Formacao;
import br.com.easylearn.repository.ModuloRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FormacoesDto {

    private Long id;
    private String titulo;
    private String descricao;
    private Integer quantidadeDeCursos;
    private String categoria;

    public FormacoesDto(Formacao formacao) {
        this.id = formacao.getId();
        this.titulo = formacao.getTitulo();
        this.descricao = formacao.getDescricao();
        this.quantidadeDeCursos = formacao.getCursoList().size();
        this.categoria = formacao.getCategoria().getNome();
    }

    public static List<FormacoesDto> converter(List<Formacao> allFormacoes) {
        return allFormacoes.stream().map(FormacoesDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public Integer getQuantidadeDeCursos() {
        return quantidadeDeCursos;
    }
}
