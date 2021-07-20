package br.com.easylearn.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Reforco extends AbstractEntity{
    @OneToOne
    private Tutor tutor;
    @OneToOne
    private Curso curso;

    public Reforco() {
    }

    public Reforco(Tutor tutor, Curso curso) {
        this.tutor = tutor;
        this.curso = curso;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
