package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Aluno;
import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Matricula;
import br.com.easylearn.repository.AlunoRepository;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.MatriculaRepository;

public class MatriculaForm {

    private Long idAluno;
    private Long idCurso;
    private Integer progresso;
    private String estado;

    public Matricula save(MatriculaRepository matriculaRepository, AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        Aluno aluno = alunoRepository.getOne(idAluno) ;
        Curso curso = cursoRepository.getOne(idCurso);

        Matricula matricula = new Matricula(aluno,curso,progresso,estado);
        return matriculaRepository.save(matricula);
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public void setProgresso(Integer progresso) {
        this.progresso = progresso;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public Long getIdCurso() {
        return idCurso;
    }
}
