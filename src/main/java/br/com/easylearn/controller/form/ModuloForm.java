package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Aula;
import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Modulo;
import br.com.easylearn.repository.AulaRepository;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.ModuloRepository;

public class ModuloForm {

    private Integer indice;
    private String titulo;
    private Long idCurso;
    private Long idAula;

    public Modulo save(ModuloRepository moduloRepository, CursoRepository cursoRepository, AulaRepository aulaRepository) {
        Curso curso = cursoRepository.getOne(idCurso);
        Aula aula = aulaRepository.getOne(idAula);
        Modulo modulo = new Modulo(indice,titulo,curso,aula);
        return moduloRepository.save(modulo);
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public void setIdAula(Long idAula) {
        this.idAula = idAula;
    }
}
