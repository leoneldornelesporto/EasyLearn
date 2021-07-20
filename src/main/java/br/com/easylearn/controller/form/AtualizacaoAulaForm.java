package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Aula;
import br.com.easylearn.repository.AulaRepository;

public class AtualizacaoAulaForm {

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

    public Aula atualizar(Long idAula, AulaRepository aulaRepository) {
        Aula aula = aulaRepository.getOne(idAula);
        aula.setIndice(indice);
        aula.setTitulo(titulo);
        aula.setUrlVideo(urlVideo);
        aula.setTranscricao(transcricao);
        return aula;
    }
}
