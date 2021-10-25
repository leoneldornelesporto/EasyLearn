package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Formacao;
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
    private List<CursoDto> cursoDtoList;
    private String categoria;

    public FormacaoDto(Formacao formacao) {
        this.id = formacao.getId();
        this.titulo = formacao.getTitulo();
        this.descricao = formacao.getDescricao();
        this.subtitulo = formacao.getSubtitulo();
        this.descricaoSubtitulo = formacao.getDescricaoSubtitulo();
        this.cursoDtoList = CursoDto.converter(formacao.getCursoList());
        this.categoria = formacao.getCategoria().getNome();
    }

    public FormacaoDto(Formacao formacao, ModuloRepository moduloRepository) {
        this.id = formacao.getId();
        this.titulo = formacao.getTitulo();
        this.descricao = formacao.getDescricao();
        this.subtitulo = formacao.getSubtitulo();
        this.descricaoSubtitulo = formacao.getDescricaoSubtitulo();
        this.cursoDtoList = CursoDto.converter(formacao.getCursoList(),moduloRepository);
        this.categoria = formacao.getCategoria().getNome();
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

    public List<CursoDto> getCursoDtoList() {
        return cursoDtoList;
    }

    public String getCategoria() {
        return categoria;
    }
}
