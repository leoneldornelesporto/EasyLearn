package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Topico;

import java.util.List;
import java.util.stream.Collectors;

public class TopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private UsuarioDto autor;
    private Integer qtdResposta;

    public TopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.autor = UsuarioDto.converter(topico.getAutor());
        this.qtdResposta = topico.getRespostas().size();
    }

    public static List<TopicoDto> converter(List<Topico> all) {
        return all.stream().map(TopicoDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public UsuarioDto getAutor() {
        return autor;
    }

    public Integer getQtdResposta() {
        return qtdResposta;
    }
}
