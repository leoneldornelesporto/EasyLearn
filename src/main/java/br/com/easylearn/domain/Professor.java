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

    public Professor(String nomeCompleto, String nomeNoCertificado, String ocupacao, String cpf, String usuarioNaUrl, String email, String senha, String dataDeNascimento, String biografia, String linkedin, String github, String twitter, String empresa, String cargo, String linkPersonalizado, String avatar, String instituicao, String curso) {
        super(nomeCompleto,nomeNoCertificado,ocupacao,cpf,usuarioNaUrl,email,senha,dataDeNascimento,biografia,linkedin,github,twitter,empresa,cargo,linkPersonalizado,avatar,instituicao,curso);
        this.setAluno(Boolean.FALSE);
        this.setProfessor(Boolean.TRUE);
        this.setTutor(Boolean.FALSE);
    }

    public Professor(String nomeCompleto, String nomeNoCertificado, String ocupacao, String cpf, String usuarioNaUrl, String email, String senha, String dataDeNascimento, String biografia, String linkedin, String github, String twitter, String empresa, String cargo, String linkPersonalizado, Boolean privacidadeDoPerfil, String avatar, String instituicao, String curso) {
        super(nomeCompleto,nomeNoCertificado,ocupacao,cpf,usuarioNaUrl,email,senha,dataDeNascimento,biografia,linkedin,github,twitter,empresa,cargo,linkPersonalizado,privacidadeDoPerfil,avatar,instituicao,curso);
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
