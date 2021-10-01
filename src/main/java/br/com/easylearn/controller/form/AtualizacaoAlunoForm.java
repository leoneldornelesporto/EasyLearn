package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Aluno;
import br.com.easylearn.repository.AlunoRepository;

public class AtualizacaoAlunoForm {

    private String avatar;
    private String nomeCompleto;
    private String nomeNosCertificados;
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

    public Aluno atualizar(Long idAluno, AlunoRepository alunoRepository) {
        Aluno aluno = alunoRepository.getOne(idAluno);
        aluno.setAvatar(avatar);
        aluno.setNomeCompleto(nomeCompleto);
        aluno.setNomeNoCertificado(nomeNosCertificados);
        aluno.setUsuarioNaUrl(usuarioNaUrl);
        aluno.setCpf(cpf);
        aluno.setBiografia(biografia);
        aluno.setDataDeNascimento(dataDeNascimento);
        aluno.setOcupacao(ocupacao);
        aluno.setLinkedin(linkedin);
        aluno.setTwitter(twitter);
        aluno.setLinkPersonalizado(linkPersonalizado);
        aluno.setGithub(github);
        aluno.setEmpresa(empresa);
        aluno.setCargo(cargo);
        aluno.setPrivacidadeDoPerfil(privacidadeDoPerfil);
        aluno.setInstituicao(instituicao);
        aluno.setCurso(curso);
        return aluno;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setNomeNosCertificados(String nomeNosCertificados) {
        this.nomeNosCertificados = nomeNosCertificados;
    }

    public void setUsuarioNaUrl(String usuarioNaUrl) {
        this.usuarioNaUrl = usuarioNaUrl;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public void setLinkPersonalizado(String linkPersonalizado) {
        this.linkPersonalizado = linkPersonalizado;
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

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
