package br.com.easylearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("ALUNO")
public class Aluno extends Usuario{
    @JsonIgnore
    @OneToMany
    private List<Matricula> matriculaList;

    public Aluno() {
        this.setAluno(Boolean.TRUE);
        this.setProfessor(Boolean.FALSE);
        this.setTutor(Boolean.FALSE);
    }

    public Aluno(String nomeCompleto, String nomeNoCertificado, String usuarioNaUrl, String email, String senha, String biografia, String linkedin, String github, String twitter, String empresa, String cargo) {
        super(nomeCompleto,nomeNoCertificado,usuarioNaUrl,email,senha,biografia,linkedin,github,twitter,empresa,cargo);
        this.setAluno(Boolean.TRUE);
        this.setProfessor(Boolean.FALSE);
        this.setTutor(Boolean.FALSE);
    }

    public Aluno(String nomeCompleto, String nomeNoCertificado, String usuarioNaUrl, String email, String senha, String biografia, String linkedin, String github, String twitter, String empresa, String cargo, Boolean privacidadeDoPerfil) {
        super(nomeCompleto,nomeNoCertificado,usuarioNaUrl,email,senha,biografia,linkedin,github,twitter,empresa,cargo,privacidadeDoPerfil);
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }
}
