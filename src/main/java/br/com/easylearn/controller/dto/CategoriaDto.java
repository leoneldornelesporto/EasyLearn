package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Categoria;

public class CategoriaDto {
    private Long id;
    private String nome;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
