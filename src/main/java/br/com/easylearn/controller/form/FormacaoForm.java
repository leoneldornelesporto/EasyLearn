package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Categoria;
import br.com.easylearn.domain.Formacao;
import br.com.easylearn.repository.CategoriaRepository;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.FormacaoRepository;

public class FormacaoForm {
    private String titulo;
    private String descricao;
    private Long idCategoria;


    public Formacao save(FormacaoRepository formacaoRepository, CursoRepository cursoRepository, CategoriaRepository categoriaRepository) {
        Formacao formacao = new Formacao(titulo,descricao);
        Categoria categoria = categoriaRepository.getOne(idCategoria);
        formacao.setCategoria(categoria);

        return formacaoRepository.save(formacao);
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
}
