package br.com.easylearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.org.apache.xpath.internal.operations.Mod;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Aula extends AbstractEntity{

    private Integer indice;
    private String titulo;
    private String urlVideo;
    private String transcricao;
    private Boolean visualizada = Boolean.FALSE;

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

    public Boolean getVisualizada() {
        return visualizada;
    }

    public void setVisualizada(Boolean visualizada) {
        this.visualizada = visualizada;
    }

    public void setTranscricao(String transcricao) {
        this.transcricao = transcricao;
    }
}
