package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Reforco;
import br.com.easylearn.domain.Tutor;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.ReforcoRepository;
import br.com.easylearn.repository.TutorRepository;

public class ReforcoForm {

    private Long idTutor;
    private Long idCurso;

    public void setIdTutor(Long idTutor) {
        this.idTutor = idTutor;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public Reforco save(TutorRepository tutorRepository, ReforcoRepository reforcoRepository, CursoRepository cursoRepository) {
        Tutor tutor = tutorRepository.getOne(idTutor);
        Curso curso = cursoRepository.getOne(idCurso);
        Reforco reforco = new Reforco(tutor,curso);
        return  reforcoRepository.save(reforco);
    }
}
