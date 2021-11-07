package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Topico;
import br.com.easylearn.repository.TopicosRepository;

public class AtualizacaoTopicoForm {
    private String titulo;
    private String mensagem;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico atualizar(Long id, TopicosRepository topicoRepository) {
        Topico topico = topicoRepository.getOne(id);

        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;
    }
}
