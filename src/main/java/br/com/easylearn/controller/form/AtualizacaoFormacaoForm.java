package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Categoria;
import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Formacao;
import br.com.easylearn.repository.CategoriaRepository;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.FormacaoRepository;

import java.util.ArrayList;
import java.util.List;

public class AtualizacaoFormacaoForm {

    private String titulo;
    private String descricao;
    private String subtitulo;
    private String descricaoSubtitulo;
    private Long idCategoria;

    public Formacao atualizar(Long idFormacao, FormacaoRepository formacaoRepository, CategoriaRepository categoriaRepository) {
        Formacao formacao = formacaoRepository.getOne(idFormacao);
        formacao.setTitulo(titulo);
        formacao.setDescricao(descricao);
        formacao.setTitulo(titulo);
        formacao.setDescricaoSubtitulo(descricaoSubtitulo);

        Categoria categoria = categoriaRepository.getOne(idCategoria);

        formacao.setCategoria(categoria);

        return formacao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public void setDescricaoSubtitulo(String descricaoSubtitulo) {
        this.descricaoSubtitulo = descricaoSubtitulo;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
}
