package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Aula;
import br.com.easylearn.repository.AulaRepository;

public class AulaForm {

    private Integer indice;
    private String titulo;
    private String urlVideo;
    private String transcricao;

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public void setTranscricao(String transcricao) {
        this.transcricao = transcricao;
    }

    public Aula save(AulaRepository aulaRepository) {
        Aula aula = new Aula(indice,titulo,urlVideo,transcricao);
        return aulaRepository.save(aula);
    }
}
