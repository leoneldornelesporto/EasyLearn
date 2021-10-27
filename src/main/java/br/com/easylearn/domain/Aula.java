package br.com.easylearn.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Aula extends AbstractEntity{

    private Integer indice;
    private String titulo;
    private String urlVideo;
    private String transcricao;
    @ManyToOne
    private Modulo modulo;

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

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }
}
