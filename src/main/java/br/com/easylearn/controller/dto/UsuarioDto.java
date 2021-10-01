package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Usuario;
import org.springframework.data.domain.Page;

public class UsuarioDto {

    private Long id;
    private String avatar;
    private String nomeCompleto;
    private String nomeNoCertificado;
    private String email;
    private String usuarioNaUrl;
    private String cpf;
    private String biografia;
    private String dataDeNascimento;
    private String ocupacao;
    private String linkedin;
    private String twitter;
    private String github;
    private String linkPersonalizado;
    private String empresa;
    private String cargo;
    private String instituicao;
    private String curso;
    private Boolean privacidadeDoPerfil;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.avatar = usuario.getAvatar();
        this.nomeCompleto = usuario.getNomeCompleto();
        this.nomeNoCertificado = usuario.getNomeNoCertificado();
        this.cpf = usuario.getCpf();
        this.usuarioNaUrl = usuario.getUsuarioNaUrl();
        this.dataDeNascimento = usuario.getDataDeNascimento();
        this.ocupacao = usuario.getOcupacao();
        this.email = usuario.getEmail();
        this.biografia = usuario.getBiografia();
        this.linkedin = usuario.getLinkedin();
        this.github = usuario.getGithub();
        this.linkPersonalizado = usuario.getLinkPersonalizado();
        this.twitter = usuario.getTwitter();
        this.empresa = usuario.getEmpresa();
        this.cargo = usuario.getCargo();
        this.instituicao = usuario.getInstituicao();
        this.curso = usuario.getCurso();
        this.privacidadeDoPerfil = usuario.getPrivacidadeDoPerfil();
    }

    public static Page<UsuarioDto> converter(Page<Usuario> usuarios) {
        return usuarios.map(UsuarioDto::new);
    }

    public static UsuarioDto converter(Usuario usuario) {
        return new UsuarioDto(usuario);
    }

    public Long getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
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

    public String getDataDeNascimento() {
        return dataDeNascimento;
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

    public String getOcupacao() {
        return ocupacao;
    }

    public String getLinkPersonalizado() {
        return linkPersonalizado;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public String getCurso() {
        return curso;
    }
}
