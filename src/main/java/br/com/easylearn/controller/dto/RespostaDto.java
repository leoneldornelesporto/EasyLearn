package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Resposta;

public class RespostaDto {
    private Long id;
    private String mensagem;
    private String nomeAutor;
    private String cargo;
    private String avatar;

    public RespostaDto(Resposta resposta) {
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.nomeAutor = resposta.getAutor().getNomeCompleto();
        this.cargo = resposta.getAutor().getCargo();
        this.avatar = resposta.getAutor().getAvatar();
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public String getCargo() {
        return cargo;
    }

    public String getAvatar() {
        return avatar;
    }
}
