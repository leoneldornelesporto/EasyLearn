package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Aluno;
import br.com.easylearn.repository.AlunoRepository;

public class AtualizacaoAlunoForm {

    private String nomeCompleto;
    private String nomeNoCertificado;
    private String cpf;
    private String usuarioNaUrl;
    private String email;
    private String senha;
    private String biografia;
    private String linkedin;
    private String github;
    private String twitter;
    private String empresa;
    private String cargo;
    private Boolean privacidadeDoPerfil;

    public Aluno atualizar(Long idAluno, AlunoRepository alunoRepository) {
        Aluno aluno = alunoRepository.getOne(idAluno);
        aluno.setNomeCompleto(nomeCompleto);
        aluno.setNomeNoCertificado(nomeNoCertificado);
        aluno.setCpf(cpf);
        aluno.setUsuarioNaUrl(usuarioNaUrl);
        aluno.setEmail(email);
        aluno.setSenha(senha);
        aluno.setBiografia(biografia);
        aluno.setLinkedin(linkedin);
        aluno.setGithub(github);
        aluno.setTwitter(twitter);
        aluno.setEmpresa(empresa);
        aluno.setCargo(cargo);
        aluno.setPrivacidadeDoPerfil(privacidadeDoPerfil);
        return aluno;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setNomeNoCertificado(String nomeNoCertificado) {
        this.nomeNoCertificado = nomeNoCertificado;
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

    public void setPrivacidadeDoPerfil(Boolean privacidadeDoPerfil) {
        this.privacidadeDoPerfil = privacidadeDoPerfil;
    }
}
