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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "cpf")})
public abstract class Usuario extends AbstractEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String uuid = UUID.randomUUID().toString().replace("-","");
    private String nomeCompleto;
    private String nomeNoCertificado;
    @CPF(message = "Invalid CPF")
    @Column(unique = true, nullable = false)
    private String cpf;
    private String usuarioNaUrl;
    @Email(message = "Invalid e-mail")
    @Column(unique = true, nullable = false)
    private String email;
    private String senha;
    private String biografia;
    private String linkedin;
    private String github;
    private String twitter;
    private String empresa;
    private String cargo;
    private Boolean privacidadeDoPerfil = Boolean.FALSE;
    private Boolean ativo = Boolean.FALSE;
    @JsonIgnore
    @OneToMany
    private List<Ocorrencia> ocorrenciaList;

    private Boolean isAluno;
    private Boolean isProfessor;
    private Boolean isTutor;

    public Usuario() {
    }

    public Usuario(String nomeCompleto, String nomeNoCertificado, String usuarioNaUrl, String email, String senha, String biografia, String linkedin, String github, String twitter, String empresa, String cargo) {
        this.nomeCompleto = nomeCompleto;
        this.nomeNoCertificado = nomeNoCertificado;
        this.usuarioNaUrl = usuarioNaUrl;
        this.email = email;
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.biografia = biografia;
        this.linkedin = linkedin;
        this.github = github;
        this.twitter = twitter;
        this.empresa = empresa;
        this.cargo = cargo;
    }

    public Usuario(String nomeCompleto, String nomeNoCertificado, String usuarioNaUrl, String email, String senha, String biografia, String linkedin, String github, String twitter, String empresa, String cargo, Boolean privacidadeDoPerfil) {
        this.nomeCompleto = nomeCompleto;
        this.nomeNoCertificado = nomeNoCertificado;
        this.usuarioNaUrl = usuarioNaUrl;
        this.email = email;
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.biografia = biografia;
        this.linkedin = linkedin;
        this.github = github;
        this.twitter = twitter;
        this.empresa = empresa;
        this.cargo = cargo;
        this.privacidadeDoPerfil = privacidadeDoPerfil;
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
