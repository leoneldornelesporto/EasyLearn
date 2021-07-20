package br.com.easylearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Matricula extends AbstractEntity{
    @OneToOne
    private Aluno aluno;
    @JsonIgnore
    @OneToOne
    private Curso curso;
    private Integer progresso;
    private String estado;
    private Boolean cursoConcluido = Boolean.FALSE;

    public Matricula() {
    }

    public Matricula(Aluno aluno, Curso curso, Integer progresso, String estado) {
        this.aluno = aluno;
        this.curso = curso;
        this.progresso = progresso;
        this.estado = estado;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Integer getProgresso() {
        return progresso;
    }

    public void setProgresso(Integer progresso) {
        this.progresso = progresso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getCursoConcluido() {
        return cursoConcluido;
    }

    public void setCursoConcluido(Boolean cursoConcluido) {
        this.cursoConcluido = cursoConcluido;
    }
}
