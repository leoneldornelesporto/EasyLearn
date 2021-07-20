package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Categoria;
import br.com.easylearn.repository.CategoriaRepository;

public class CategoriaForm {
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria save(CategoriaRepository categoriaRepository) {
        Categoria categoria = new Categoria(nome);
        return categoriaRepository.save(categoria);
    }
}
