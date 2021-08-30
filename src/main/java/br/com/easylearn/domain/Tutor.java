package br.com.easylearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("TUTOR")
public class Tutor extends Usuario{
    @JsonIgnore
    @OneToMany
    private List<Reforco> reforcoList;

    public Tutor() {
        this.setAluno(Boolean.FALSE);
        this.setProfessor(Boolean.FALSE);
        this.setTutor(Boolean.TRUE);
    }

    public Tutor(String nomeCompleto, String nomeNoCertificado, String cpf, String usuarioNaUrl, String email, String senha, String biografia, String linkedin, String github, String twitter, String empresa, String cargo) {
        super(nomeCompleto,nomeNoCertificado,cpf,usuarioNaUrl,email,senha,biografia,linkedin,github,twitter,empresa,cargo);
        this.setAluno(Boolean.FALSE);
        this.setProfessor(Boolean.FALSE);
        this.setTutor(Boolean.TRUE);
    }

    public Tutor(String nomeCompleto, String nomeNoCertificado, String cpf, String usuarioNaUrl, String email, String senha, String biografia, String linkedin, String github, String twitter, String empresa, String cargo, Boolean privacidadeDoPerfil) {
        super(nomeCompleto,nomeNoCertificado,cpf,usuarioNaUrl,email,senha,biografia,linkedin,github,twitter,empresa,cargo,privacidadeDoPerfil);
        this.setAluno(Boolean.FALSE);
        this.setProfessor(Boolean.FALSE);
        this.setTutor(Boolean.TRUE);
    }

    public List<Reforco> getReforcoList() {
        return reforcoList;
    }

    public void setReforcoList(List<Reforco> reforcoList) {
        this.reforcoList = reforcoList;
    }
}
