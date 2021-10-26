package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Categoria;
import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Formacao;
import br.com.easylearn.repository.CategoriaRepository;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.FormacaoRepository;

public class AtualizacaoCursoForm {

    private String nome;
    private String descricao;
    private Integer cargahoraria;
    private Long idCategoria;
    private String imagemIcon;
    private Boolean ativo;
    private Long idFormacao;

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

    public void setIdFormacao(Long idFormacao) {
        this.idFormacao = idFormacao;
    }

    public Curso atualizar(Long idCurso, CursoRepository cursoRepository, CategoriaRepository categoriaRepository, FormacaoRepository formacaoRepository) {
        Curso curso = cursoRepository.getOne(idCurso);
        Formacao byId = formacaoRepository.getById(idFormacao);
        curso.setFormacao(byId);
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
