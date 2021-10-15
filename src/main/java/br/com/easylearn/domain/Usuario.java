package br.com.easylearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_USUARIO", discriminatorType = DiscriminatorType.STRING)
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "cpf"), @UniqueConstraint(columnNames = "email")})
public abstract class Usuario extends AbstractEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String uuid = UUID.randomUUID().toString().replace("-","");
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
    private Boolean privacidadeDoPerfil = Boolean.FALSE;
    private Boolean ativo = Boolean.TRUE;
    @JsonIgnore
    @OneToMany
    private List<Ocorrencia> ocorrenciaList;

    private Boolean isAluno;
    private Boolean isProfessor;
    private Boolean isTutor;

    private String avatar;
    private String instituicao;
    private String curso;

    public Usuario() {
    }

    public Usuario(String nomeCompleto, String nomeNoCertificado, String ocupacao, String cpf, String usuarioNaUrl, String email, String senha, String dataDeNascimento, String biografia, String linkedin, String github, String twitter, String empresa, String cargo, String linkPersonalizado, String avatar, String instituicao, String curso) {
        this.nomeCompleto = nomeCompleto;
        this.nomeNoCertificado = nomeNoCertificado;
        this.ocupacao = ocupacao;
        this.cpf = cpf;
        this.usuarioNaUrl = usuarioNaUrl;
        this.email = email;
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.dataDeNascimento = dataDeNascimento;
        this.biografia = biografia;
        this.linkedin = linkedin;
        this.github = github;
        this.twitter = twitter;
        this.empresa = empresa;
        this.cargo = cargo;
        this.linkPersonalizado = linkPersonalizado;
        this.avatar = avatar;
        this.instituicao = instituicao;
        this.curso = curso;
    }

    public Usuario(String nomeCompleto, String nomeNoCertificado, String ocupacao, String cpf, String usuarioNaUrl, String email, String senha, String dataDeNascimento, String biografia, String linkedin, String github, String twitter, String empresa, String cargo, String linkPersonalizado, Boolean privacidadeDoPerfil, String avatar, String instituicao, String curso) {
        this.nomeCompleto = nomeCompleto;
        this.nomeNoCertificado = nomeNoCertificado;
        this.ocupacao = ocupacao;
        this.cpf = cpf;
        this.usuarioNaUrl = usuarioNaUrl;
        this.email = email;
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.dataDeNascimento = dataDeNascimento;
        this.biografia = biografia;
        this.linkedin = linkedin;
        this.github = github;
        this.twitter = twitter;
        this.empresa = empresa;
        this.cargo = cargo;
        this.linkPersonalizado = linkPersonalizado;
        this.privacidadeDoPerfil = privacidadeDoPerfil;
        this.avatar = avatar;
        this.instituicao = instituicao;
        this.curso = curso;
    }

    public String getUuid() {
        return uuid;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeNoCertificado() {
        return nomeNoCertificado;
    }

    public void setNomeNoCertificado(String nomeNoCertificado) {
        this.nomeNoCertificado = nomeNoCertificado;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String getUsuarioNaUrl() {
        return usuarioNaUrl;
    }

    public void setUsuarioNaUrl(String usuarioNaUrl) {
        this.usuarioNaUrl = usuarioNaUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLinkPersonalizado() {
        return linkPersonalizado;
    }

    public void setLinkPersonalizado(String linkPersonalizado) {
        this.linkPersonalizado = linkPersonalizado;
    }

    public Boolean getPrivacidadeDoPerfil() {
        return privacidadeDoPerfil;
    }

    public void setPrivacidadeDoPerfil(Boolean privacidadeDoPerfil) {
        this.privacidadeDoPerfil = privacidadeDoPerfil;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Ocorrencia> getOcorrenciaList() {
        return ocorrenciaList;
    }

    public void setOcorrenciaList(List<Ocorrencia> ocorrenciaList) {
        this.ocorrenciaList = ocorrenciaList;
    }

    public Boolean getAluno() {
        return isAluno;
    }

    public void setAluno(Boolean aluno) {
        isAluno = aluno;
    }

    public Boolean getProfessor() {
        return isProfessor;
    }

    public void setProfessor(Boolean professor) {
        isProfessor = professor;
    }

    public Boolean getTutor() {
        return isTutor;
    }

    public void setTutor(Boolean tutor) {
        isTutor = tutor;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "uuid='" + uuid + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", nomeNoCertificado='" + nomeNoCertificado + '\'' +
                ", ocupacao='" + ocupacao + '\'' +
                ", cpf='" + cpf + '\'' +
                ", usuarioNaUrl='" + usuarioNaUrl + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dataDeNascimento='" + dataDeNascimento + '\'' +
                ", biografia='" + biografia + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", github='" + github + '\'' +
                ", twitter='" + twitter + '\'' +
                ", empresa='" + empresa + '\'' +
                ", cargo='" + cargo + '\'' +
                ", linkPersonalizado='" + linkPersonalizado + '\'' +
                ", privacidadeDoPerfil=" + privacidadeDoPerfil +
                ", ativo=" + ativo +
                ", ocorrenciaList=" + ocorrenciaList +
                ", isAluno=" + isAluno +
                ", isProfessor=" + isProfessor +
                ", isTutor=" + isTutor +
                ", avatar='" + avatar + '\'' +
                ", instituicao='" + instituicao + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nomeCompleto;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
