package br.com.easylearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Formacao extends AbstractEntity{
    private String titulo;
    private String descricao;
    private String subtitulo;
    private String descricaoSubtitulo;
    @JsonIgnore
    @ManyToMany
    private List<Curso> cursoList;
    @OneToOne
    private Categoria categoria;

    public Formacao() {
    }

    public Formacao(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDescricaoSubtitulo() {
        return descricaoSubtitulo;
    }

    public void setDescricaoSubtitulo(String descricaoSubtitulo) {
        this.descricaoSubtitulo = descricaoSubtitulo;
    }
}
