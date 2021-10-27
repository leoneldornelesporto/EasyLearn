package br.com.easylearn.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Modulo extends AbstractEntity{

    private Integer indice;
    private String titulo;
    private String tituloSecundario;
    @ManyToOne
    private Curso curso;

    public Modulo() {

    }

    public Modulo(Integer indice, String titulo, String tituloSecundario) {
        this.indice = indice;
        this.titulo = titulo;
        this.tituloSecundario = tituloSecundario;
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
