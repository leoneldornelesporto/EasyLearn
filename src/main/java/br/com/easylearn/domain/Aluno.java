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

    public Aluno(String nomeCompleto, String nomeNoCertificado, String ocupacao, String cpf, String usuarioNaUrl, String email, String senha, String dataDeNascimento, String biografia, String linkedin, String github, String twitter, String empresa, String cargo, String linkPersonalizado, String avatar, String instituicao, String curso) {
        super(nomeCompleto,nomeNoCertificado,ocupacao,cpf,usuarioNaUrl,email,senha,dataDeNascimento,biografia,linkedin,github,twitter,empresa,cargo,linkPersonalizado,avatar,instituicao,curso);
        this.setAluno(Boolean.TRUE);
        this.setProfessor(Boolean.FALSE);
        this.setTutor(Boolean.FALSE);
    }

    public Aluno(String nomeCompleto, String nomeNoCertificado, String ocupacao, String cpf, String usuarioNaUrl, String email, String senha, String dataDeNascimento, String biografia, String linkedin, String github, String twitter, String empresa, String cargo, String linkPersonalizado, Boolean privacidadeDoPerfil, String avatar, String instituicao, String curso) {
        super(nomeCompleto,nomeNoCertificado,ocupacao,cpf,usuarioNaUrl,email,senha,dataDeNascimento,biografia,linkedin,github,twitter,empresa,cargo,linkPersonalizado,privacidadeDoPerfil,avatar,instituicao,curso);
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }
}
