package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Ministra;
import br.com.easylearn.domain.Professor;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.MinistraRepository;
import br.com.easylearn.repository.ProfessorRepository;

public class MinistraForm {
    
    private Long idProfessor;
    private Long idCurso;

    public Ministra save(MinistraRepository ministraRepository, ProfessorRepository professorRepository, CursoRepository cursoRepository) {
        Professor professor = professorRepository.getOne(idProfessor);
        Curso curso = cursoRepository.getOne(idCurso);
        Ministra ministra = new Ministra(professor,curso);
        return ministraRepository.save(ministra);
    }

    public void setIdProfessor(Long idProfessor) {
        this.idProfessor = idProfessor;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }
}
