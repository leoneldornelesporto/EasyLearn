package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Categoria;
import br.com.easylearn.domain.Formacao;
import br.com.easylearn.repository.CategoriaRepository;
import br.com.easylearn.repository.FormacaoRepository;

public class FormacaoForm {

    private String titulo;
    private String descricao;
    private String subtitulo;
    private String descricaoSubtitulo;
    private Long idCategoria;


    public Formacao save(FormacaoRepository formacaoRepository, CategoriaRepository categoriaRepository) {
        Formacao formacao = new Formacao(titulo,descricao,subtitulo,descricaoSubtitulo);
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
