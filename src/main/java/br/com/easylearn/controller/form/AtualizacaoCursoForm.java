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
    private Long categoriaId;
    private String imagemIcon;
    private Boolean ativo;
    private Integer transcricao;
    private Integer valorCurso;
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

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void setImagemIcon(String imagemIcon) {
        this.imagemIcon = imagemIcon;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setTranscricao(Integer transcricao) {
        this.transcricao = transcricao;
    }

    public void setValorCurso(Integer valorCurso) {
        this.valorCurso = valorCurso;
    }

    public void setIdFormacao(Long idFormacao) {
        this.idFormacao = idFormacao;
    }

    public Curso atualizar(Long idCurso, CursoRepository cursoRepository, CategoriaRepository categoriaRepository, FormacaoRepository formacaoRepository) {
        Curso curso = cursoRepository.getOne(idCurso);
        Formacao formacao = formacaoRepository.getById(idFormacao);
        curso.setFormacao(formacao);
        curso.setNome(nome);
        curso.setDescricao(descricao);
        curso.setCargaHoraria(cargahoraria);
        Categoria categoria = categoriaRepository.getOne(categoriaId);
        curso.setImagemIcon(imagemIcon);
        curso.setCategoria(categoria);
        curso.setAtivo(ativo);
        curso.setTranscricao(transcricao);
        curso.setValorCurso(valorCurso);
        return curso;
    }
}
