package br.com.easylearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Modulo extends AbstractEntity{

    private Integer indice;
    private String titulo;
    private String tituloSecundario;
    @ElementCollection
    private List<String> subtitulo = new ArrayList<>();
    @JsonIgnore
    @OneToOne
    private Curso curso;
    @OneToMany
    private List<Aula> aulaList;

    public Modulo(Integer indice, String titulo, String tituloSecundario,Curso curso, List<Aula> aulaList, List<String> subtitulo) {
        this.indice = indice;
        this.titulo = titulo;
        this.tituloSecundario = tituloSecundario;
        this.curso = curso;
        this.aulaList = aulaList;
        this.subtitulo = subtitulo;
    }

    public Modulo() {

    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTituloSecundario() {
        return tituloSecundario;
    }

    public void setTituloSecundario(String tituloSecundario) {
        this.tituloSecundario = tituloSecundario;
    }

    public List<String> getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(List<String> subtitulo) {
        this.subtitulo = subtitulo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Aula> getAulaList() {
        return aulaList;
    }

    public void setAulaList(List<Aula> aulaList) {
        this.aulaList = aulaList;
    }
}
