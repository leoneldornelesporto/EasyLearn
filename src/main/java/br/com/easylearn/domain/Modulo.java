package br.com.easylearn.domain;

import javax.persistence.Entity;

@Entity
public class Modulo extends AbstractEntity{

    private Integer indice;
    private String titulo;
    private String tituloSecundario;

    public Modulo(Integer indice, String titulo, String tituloSecundario) {
        this.indice = indice;
        this.titulo = titulo;
        this.tituloSecundario = tituloSecundario;
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
}
