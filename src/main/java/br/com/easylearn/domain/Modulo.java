package br.com.easylearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Modulo extends AbstractEntity{

    private Integer indice;
    private String titulo;
    @JsonIgnore
    @OneToOne
    private Curso curso;
    @OneToOne
    private Aula aula;

    public Modulo() {
    }

    public Modulo(Integer indice, String titulo, Curso curso, Aula aula) {
        this.indice = indice;
        this.titulo = titulo;
        this.curso = curso;
        this.aula = aula;
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }
}
