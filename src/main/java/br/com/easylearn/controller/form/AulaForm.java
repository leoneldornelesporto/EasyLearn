package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Aula;
import br.com.easylearn.domain.Modulo;
import br.com.easylearn.repository.AulaRepository;
import br.com.easylearn.repository.ModuloRepository;

public class AulaForm {

    private Integer indice;
    private String titulo;
    private String urlVideo;
    private String transcricao;
    private Long idModulo;

    public Aula save(AulaRepository aulaRepository, ModuloRepository moduloRepository) {
        Aula aula = new Aula(indice,titulo,urlVideo,transcricao);

        Modulo modulo = moduloRepository.getOne(idModulo);
        aula.setModulo(modulo);

        return aulaRepository.save(aula);
    }

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

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }
}
