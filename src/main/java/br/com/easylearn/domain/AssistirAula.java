package br.com.easylearn.domain;

import javax.persistence.Entity;

@Entity
public class AssistirAula extends AbstractEntity{

    private Long idAluno;
    private String uuidCurso;
    private Long idAula;
    private Boolean assistido = Boolean.FALSE;

    public AssistirAula() {
    }

    public AssistirAula(Long idAluno, String uuidCurso, Long idAula) {
        this.idAluno = idAluno;
        this.uuidCurso = uuidCurso;
        this.idAula = idAula;
        this.assistido = Boolean.TRUE;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public String getUuidCurso() {
        return uuidCurso;
    }

    public void setUuidCurso(String uuidCurso) {
        this.uuidCurso = uuidCurso;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Long getIdAula() {
        return idAula;
    }

    public void setIdAula(Long idAula) {
        this.idAula = idAula;
    }

    public Boolean getAssistido() {
        return assistido;
    }

    public void setAssistido(Boolean assistido) {
        this.assistido = assistido;
    }
}
