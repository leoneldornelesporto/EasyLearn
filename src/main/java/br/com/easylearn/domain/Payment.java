package br.com.easylearn.domain;

import javax.persistence.Entity;

@Entity
public class Payment extends AbstractEntity{
    private String idPayment;
    private String status;
    private String uuidCurso;
    private Long idAluno;

    public String getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(String idPayment) {
        this.idPayment = idPayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUuidCurso() {
        return uuidCurso;
    }

    public void setUuidCurso(String uuidCurso) {
        this.uuidCurso = uuidCurso;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }
}
