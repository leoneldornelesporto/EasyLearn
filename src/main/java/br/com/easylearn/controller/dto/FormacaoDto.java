package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.AssistirAula;
import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Formacao;
import br.com.easylearn.repository.AssistirAulaRepository;
import br.com.easylearn.repository.ModuloRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FormacaoDto {

    private Long id;
    private String titulo;
    private String descricao;
    private String subtitulo;
    private String descricaoSubtitulo;
    private String categoria;
    private Integer qtdCursos;

    public FormacaoDto(Formacao formacao) {
        this.id = formacao.getId();
        this.titulo = formacao.getTitulo();
        this.descricao = formacao.getDescricao();
        this.subtitulo = formacao.getSubtitulo();
        this.descricaoSubtitulo = formacao.getDescricaoSubtitulo();
        this.categoria = formacao.getCategoria().getNome();
        this.qtdCursos = formacao.getCursoList().size();
    }

    public FormacaoDto(Formacao formacao, AssistirAulaRepository assistirAulaRepository) {
        this.id = formacao.getId();
        this.titulo = formacao.getTitulo();
        this.descricao = formacao.getDescricao();
        this.subtitulo = formacao.getSubtitulo();
        this.descricaoSubtitulo = formacao.getDescricaoSubtitulo();
        this.categoria = formacao.getCategoria().getNome();
    }

    public FormacaoDto(Formacao formacao, ModuloRepository moduloRepository) {
        this.id = formacao.getId();
        this.titulo = formacao.getTitulo();
        this.descricao = formacao.getDescricao();
        this.subtitulo = formacao.getSubtitulo();
        this.descricaoSubtitulo = formacao.getDescricaoSubtitulo();
        this.categoria = formacao.getCategoria().getNome();
    }

    public static FormacaoDto converter(Formacao formacao) {
        return new FormacaoDto(formacao);
    }

    public static List<FormacaoDto> converter(List<Formacao> allFormacoes) {
        return allFormacoes.stream().map(FormacaoDto::new).collect(Collectors.toList());
    }

    public static List<FormacaoDto> converter(List<Formacao> allFormacoes, ModuloRepository moduloRepository) {
        List<FormacaoDto> formacaoDtoList = new ArrayList<>();

        for(Formacao formacao:allFormacoes){
            FormacaoDto formacaoDto = new FormacaoDto(formacao,moduloRepository);
            formacaoDtoList.add(formacaoDto);
        }

        return formacaoDtoList;
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

    public String getSubtitulo() {
        return subtitulo;
    }

    public String getDescricaoSubtitulo() {
        return descricaoSubtitulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public Integer getQtdCursos() {
        return qtdCursos;
    }
}
