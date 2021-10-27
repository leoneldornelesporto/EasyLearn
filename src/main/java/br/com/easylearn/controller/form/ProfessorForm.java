package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Professor;
import br.com.easylearn.domain.Tutor;
import br.com.easylearn.repository.ProfessorRepository;

import java.util.Optional;

public class ProfessorForm {

    private String nomeCompleto;
    private String nomeNoCertificado;
    private String ocupacao;
    private String cpf;
    private String usuarioNaUrl;
    private String email;
    private String senha;
    private String dataDeNascimento;
    private String biografia;
    private String linkedin;
    private String github;
    private String twitter;
    private String empresa;
    private String cargo;
    private String linkPersonalizado;
    private String avatar;
    private String instituicao;
    private String curso;

    public Professor save(ProfessorRepository professorRepository) {
        Optional<Professor> byCpf = professorRepository.findByCpf(cpf);

        if(byCpf.isPresent()){
            return null;
        }else{
            Professor professor = new Professor(nomeCompleto,nomeNoCertificado,ocupacao,cpf,usuarioNaUrl,email,senha,dataDeNascimento,biografia,linkedin,github,twitter,empresa,cargo,linkPersonalizado,avatar,instituicao,curso);
            return professorRepository.save(professor);
        }
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setNomeNoCertificado(String nomeNoCertificado) {
        this.nomeNoCertificado = nomeNoCertificado;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setUsuarioNaUrl(String usuarioNaUrl) {
        this.usuarioNaUrl = usuarioNaUrl;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setLinkPersonalizado(String linkPersonalizado) {
        this.linkPersonalizado = linkPersonalizado;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
