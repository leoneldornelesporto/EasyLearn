package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Formacao;
import br.com.easylearn.repository.CursoRepository;

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

    public FormacoesDto(Formacao formacao, CursoRepository cursoRepository) {
        this.id = formacao.getId();
        this.titulo = formacao.getTitulo();
        this.descricao = formacao.getDescricao();
        this.quantidadeDeCursos = cursoRepository.findAllByFormacaoId(formacao.getId()).size();
        this.categoria = formacao.getCategoria().getNome();
    }

    public static List<FormacoesDto> converter(List<Formacao> allFormacoes, CursoRepository cursoRepository) {
        List<FormacoesDto> formacoesDtoList = new ArrayList<>();

        for (Formacao formacao:allFormacoes){
            FormacoesDto formacoesDto = new FormacoesDto(formacao,cursoRepository);
            formacoesDtoList.add(formacoesDto);
        }

        return formacoesDtoList;
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
