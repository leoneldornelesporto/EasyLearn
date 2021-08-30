package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Tutor;

import java.util.List;
import java.util.stream.Collectors;

public class TutorDto {

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

    public TutorDto(Tutor tutor) {
        this.id = tutor.getId();
        this.nomeCompleto = tutor.getNomeCompleto();
        this.nomeNoCertificado = tutor.getNomeNoCertificado();
        this.cpf = tutor.getCpf();
        this.usuarioNaUrl = tutor.getUsuarioNaUrl();
        this.email = tutor.getEmail();
        this.biografia = tutor.getBiografia();
        this.linkedin = tutor.getLinkedin();
        this.github = tutor.getGithub();
        this.twitter = tutor.getTwitter();
        this.empresa = tutor.getEmpresa();
        this.cargo = tutor.getCargo();
        this.privacidadeDoPerfil = tutor.getPrivacidadeDoPerfil();
    }

    public static List<TutorDto> converter(List<Tutor> allTutores) {
        return allTutores.stream().map(TutorDto::new).collect(Collectors.toList());
    }

    public static TutorDto converter(Tutor tutor) {
        return new TutorDto(tutor);
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
