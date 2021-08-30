package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Professor;

import java.util.List;
import java.util.stream.Collectors;

public class ProfessorDto {

    private Long id;
    private String nomeCompleto;
    private String nomeNoCertificado;
    private String cpf;
    private String usuarioNaUrl;
    private String email;
    private String biografia;
    private String linkedin;
    private String github;
    private String twitter;
    private String empresa;
    private String cargo;
    private Boolean privacidadeDoPerfil;

    public ProfessorDto(Professor professor) {
        this.id = professor.getId();
        this.nomeCompleto = professor.getNomeCompleto();
        this.nomeNoCertificado = professor.getNomeNoCertificado();
        this.cpf = professor.getCpf();
        this.usuarioNaUrl = professor.getUsuarioNaUrl();
        this.email = professor.getEmail();
        this.biografia = professor.getBiografia();
        this.linkedin = professor.getLinkedin();
        this.github = professor.getGithub();
        this.twitter = professor.getTwitter();
        this.empresa = professor.getEmpresa();
        this.cargo = professor.getCargo();
        this.privacidadeDoPerfil = professor.getPrivacidadeDoPerfil();
    }

    public static List<ProfessorDto> converter(List<Professor> allProfessores) {
        return allProfessores.stream().map(ProfessorDto::new).collect(Collectors.toList());
    }

    public static ProfessorDto converter(Professor professor) {
        return new ProfessorDto(professor);
    }

    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getNomeNoCertificado() {
        return nomeNoCertificado;
    }

    public String getCpf() {
        return cpf;
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
