package br.com.easylearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Ocorrencia extends AbstractEntity{

    @OneToOne
    private Usuario usuario;
    @JsonIgnore
    @OneToOne
    private Curso curso;
    private String descricao;
    private LocalDateTime date;

    public Ocorrencia() {
    }

    public Ocorrencia(Usuario usuario, Curso curso, String descricao, LocalDateTime date) {
        this.usuario = usuario;
        this.curso = curso;
        this.descricao = descricao;
        this.date = date;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
