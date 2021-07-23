package br.com.easylearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("PROFESSOR")
public class Professor extends Usuario{
    @JsonIgnore
    @OneToMany
    private List<Ministra> ministraList;

    public Professor() {
        this.setAluno(Boolean.FALSE);
        this.setProfessor(Boolean.TRUE);
        this.setTutor(Boolean.FALSE);
    }

    public Professor(String nomeCompleto, String nomeNoCertificado, String usuarioNaUrl, String email, String senha, String biografia, String linkedin, String github, String twitter, String empresa, String cargo) {
        super(nomeCompleto,nomeNoCertificado,usuarioNaUrl,email,senha,biografia,linkedin,github,twitter,empresa,cargo);
        this.setAluno(Boolean.FALSE);
        this.setProfessor(Boolean.TRUE);
        this.setTutor(Boolean.FALSE);
    }

    public Professor(String nomeCompleto, String nomeNoCertificado, String usuarioNaUrl, String email, String senha, String biografia, String linkedin, String github, String twitter, String empresa, String cargo, Boolean privacidadeDoPerfil) {
        super(nomeCompleto,nomeNoCertificado,usuarioNaUrl,email,senha,biografia,linkedin,github,twitter,empresa,cargo,privacidadeDoPerfil);
        this.setAluno(Boolean.FALSE);
        this.setProfessor(Boolean.TRUE);
        this.setTutor(Boolean.FALSE);
    }

    public List<Ministra> getMinistraList() {
        return ministraList;
    }

    public void setMinistraList(List<Ministra> ministraList) {
        this.ministraList = ministraList;
    }
}
