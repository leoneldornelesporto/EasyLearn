package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Categoria;
import br.com.easylearn.domain.Curso;
import br.com.easylearn.repository.CategoriaRepository;
import br.com.easylearn.repository.CursoRepository;

public class AtualizacaoCursoForm {

    private String nome;
    private String descricao;
    private Integer cargahoraria;
    private Long idCategoria;
    private String imagemIcon;
    private Boolean ativo;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCargahoraria(Integer cargahoraria) {
        this.cargahoraria = cargahoraria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setImagemIcon(String imagemIcon) {
        this.imagemIcon = imagemIcon;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Curso atualizar(Long idCurso, CursoRepository cursoRepository, CategoriaRepository categoriaRepository) {
        Curso curso = cursoRepository.getOne(idCurso);
        curso.setNome(nome);
        curso.setDescricao(descricao);
        curso.setCargaHoraria(cargahoraria);
        curso.setImagemIcon(imagemIcon);
        Categoria categoria = categoriaRepository.getOne(idCategoria);
        curso.setCategoria(categoria);
        curso.setAtivo(ativo);
        return curso;
    }
}
