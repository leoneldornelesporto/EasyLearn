package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Aula;
import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Modulo;
import br.com.easylearn.repository.AulaRepository;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.ModuloRepository;

public class AtualizacaoAulaForm {

    private Integer indice;
    private String titulo;
    private String urlVideo;
    private String transcricao;
    private Long idCurso;
    private Long idModulo;

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

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public Aula atualizar(Long idAula, AulaRepository aulaRepository, CursoRepository cursoRepository, ModuloRepository moduloRepository) {
        Aula aula = aulaRepository.getOne(idAula);
        Curso curso = cursoRepository.getOne(idCurso);
        Modulo modulo = moduloRepository.getOne(idModulo);
        aula.setIndice(indice);
        aula.setTitulo(titulo);
        aula.setUrlVideo(urlVideo);
        aula.setTranscricao(transcricao);
        aula.setCurso(curso);
        aula.setModulo(modulo);
        return aula;
    }
}
