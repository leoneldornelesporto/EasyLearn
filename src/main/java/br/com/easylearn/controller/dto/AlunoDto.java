package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Aluno;

import java.util.List;
import java.util.stream.Collectors;

public class AlunoDto {

    private String nomeCompleto;
    private String nomeNoCertificado;
    private String usuarioNaUrl;
    private String email;
    private String biografia;
    private String linkedin;
    private String github;
    private String twitter;
    private String empresa;
    private String cargo;
    private Boolean privacidadeDoPerfil;

    public AlunoDto(Aluno aluno) {
        this.nomeCompleto = aluno.getNomeCompleto();
        this.nomeNoCertificado = aluno.getNomeNoCertificado();
        this.usuarioNaUrl = aluno.getUsuarioNaUrl();
        this.email = aluno.getEmail();
        this.biografia = aluno.getBiografia();
        this.linkedin = aluno.getLinkedin();
        this.github = aluno.getGithub();
        this.twitter = aluno.getTwitter();
        this.empresa = aluno.getEmpresa();
        this.cargo = aluno.getCargo();
        this.privacidadeDoPerfil = aluno.getPrivacidadeDoPerfil();
    }

    public static List<AlunoDto> converter(List<Aluno> allAlunos) {
        return allAlunos.stream().map(AlunoDto::new).collect(Collectors.toList());
    }

    public static AlunoDto converter(Aluno aluno) {
        return new AlunoDto(aluno);
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getNomeNoCertificado() {
        return nomeNoCertificado;
    }

    public String getUsuarioNaUrl() {
        return usuarioNaUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getGithub() {
        return github;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public Boolean getPrivacidadeDoPerfil() {
        return privacidadeDoPerfil;
    }
}
