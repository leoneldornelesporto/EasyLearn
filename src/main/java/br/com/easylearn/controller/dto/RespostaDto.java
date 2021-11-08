package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Resposta;

import java.time.LocalDateTime;

public class RespostaDto {
    private Long id;
    private String mensagem;
    private String nomeAutor;

    public RespostaDto(Resposta resposta) {
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.nomeAutor = resposta.getAutor().getNomeCompleto();
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
}
