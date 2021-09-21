package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Aula;

import java.util.List;
import java.util.stream.Collectors;

public class AulaDto {

    private Long id;
    private Integer indice;
    private String titulo;
    private String urlVideo;
    private String transcricao;
    private Boolean visualizada;

    public AulaDto(Aula aula) {
        this.id = aula.getId();
        this.indice = aula.getIndice();
        this.titulo = aula.getTitulo();
        this.urlVideo = aula.getUrlVideo();
        this.transcricao = aula.getTranscricao();
        this.visualizada = aula.getVisualizada();
    }

    public static List<AulaDto> converter(List<Aula> allAula) {
        return allAula.stream().map(AulaDto::new).collect(Collectors.toList());
    }

    public static AulaDto converter(Aula aula) {
        return new AulaDto(aula);
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

    public String getUrlVideo() {
        return urlVideo;
    }

    public String getTranscricao() {
        return transcricao;
    }

    public Boolean getVisualizada() {
        return visualizada;
    }
}
