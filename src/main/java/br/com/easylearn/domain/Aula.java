package br.com.easylearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Aula extends AbstractEntity{

    private Integer indice;
    private String titulo;
    private String urlVideo;
    private String transcricao;
    @JsonIgnore
    @OneToMany
    private List<Modulo> moduloList;

    public Aula() {
    }

    public Aula(Integer indice, String titulo, String urlVideo, String transcricao) {
        this.indice = indice;
        this.titulo = titulo;
        this.urlVideo = urlVideo;
        this.transcricao = transcricao;
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

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getTranscricao() {
        return transcricao;
    }

    public void setTranscricao(String transcricao) {
        this.transcricao = transcricao;
    }

    public List<Modulo> getModuloList() {
        return moduloList;
    }

    public void setModuloList(List<Modulo> moduloList) {
        this.moduloList = moduloList;
    }
}
