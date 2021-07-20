package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Categoria;
import br.com.easylearn.repository.CategoriaRepository;

public class AtualizacaoCategoriaForm {
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria atualizar(Long idCategoria, CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.getOne(idCategoria);
        categoria.setNome(nome);
        return categoria;
    }
}
