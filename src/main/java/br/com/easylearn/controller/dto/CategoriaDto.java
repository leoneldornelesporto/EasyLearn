package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaDto {
    private Long id;
    private String nome;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public static List<CategoriaDto> converter(List<Categoria> all) {
        return all.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
