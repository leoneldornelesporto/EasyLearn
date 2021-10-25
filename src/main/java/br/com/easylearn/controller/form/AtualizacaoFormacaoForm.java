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
    private Long idCategoria;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Formacao atualizar(Long idFormacao, FormacaoRepository formacaoRepository, CursoRepository cursoRepository, CategoriaRepository categoriaRepository) {
        Formacao formacao = formacaoRepository.getOne(idFormacao);
        formacao.setTitulo(titulo);
        formacao.setDescricao(descricao);

        Categoria categoria = categoriaRepository.getOne(idCategoria);

        formacao.setCategoria(categoria);

        return formacao;
    }
}
