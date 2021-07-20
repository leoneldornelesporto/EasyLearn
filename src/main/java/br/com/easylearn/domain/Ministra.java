package br.com.easylearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Ministra extends AbstractEntity{
    @OneToOne
    private Professor professor;
    @JsonIgnore
    @OneToOne
    private Curso curso;

    public Ministra() {
    }

    public Ministra(Professor professor, Curso curso) {
        this.professor = professor;
        this.curso = curso;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
