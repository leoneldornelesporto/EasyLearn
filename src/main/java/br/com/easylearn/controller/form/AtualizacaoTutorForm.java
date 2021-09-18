package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Tutor;
import br.com.easylearn.repository.TutorRepository;

public class AtualizacaoTutorForm {

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

    public Tutor atualizar(Long idTutor, TutorRepository tutorRepository) {
        Tutor tutor = tutorRepository.getOne(idTutor);
        tutor.setNomeCompleto(nomeCompleto);
        tutor.setNomeNoCertificado(nomeNoCertificado);
        tutor.setCpf(cpf);
        tutor.setUsuarioNaUrl(usuarioNaUrl);
        tutor.setEmail(email);
        tutor.setSenha(senha);
        tutor.setBiografia(biografia);
        tutor.setLinkedin(linkedin);
        tutor.setGithub(github);
        tutor.setTwitter(twitter);
        tutor.setEmpresa(empresa);
        tutor.setCargo(cargo);
        tutor.setPrivacidadeDoPerfil(privacidadeDoPerfil);
        return tutor;
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
