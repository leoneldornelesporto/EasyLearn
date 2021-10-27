package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Modulo;
import br.com.easylearn.repository.ModuloRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CursoDto {

    private Long id;
    private String nome;
    private String descricao;
    private Integer cargaHoraria;
    private String data;
    private String categoria;
    private String imagemIcon;
    private String uuid;
    private String nomeProfessor;
    private String biografia;
    private String avatar;
    private String linkedin;
    private Boolean ativo;
    private Integer transcricao;
    private Integer valorCurso;


    public CursoDto(Curso curso) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.descricao = curso.getDescricao();
        this.cargaHoraria = curso.getCargaHoraria();
        this.data = curso.getData();
        this.categoria = curso.getCategoria().getNome();
        this.imagemIcon = curso.getImagemIcon();
        this.uuid = curso.getUuid();

        this.nomeProfessor = curso.getProfessor().getNomeCompleto();
        this.biografia = curso.getProfessor().getBiografia();
        this.avatar = curso.getProfessor().getAvatar();
        this.linkedin = curso.getProfessor().getLinkedin();
        this.ativo = curso.getAtivo();
        this.transcricao = curso.getTranscricao();
        this.valorCurso = curso.getValorCurso();
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

    public String getData() {
        return data;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getImagemIcon() {
        return imagemIcon;
    }

    public String getUuid() {
        return uuid;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Integer getTranscricao() {
        return transcricao;
    }

    public Integer getValorCurso() {
        return valorCurso;
    }
}
