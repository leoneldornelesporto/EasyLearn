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

    //private List<Long> idCursos;
    private Long idCurso;
    private String titulo;
    private String descricao;
    private Long idCategoria;

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
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

    /*
    public Formacao atualizar(Long idFormacao, FormacaoRepository formacaoRepository, CursoRepository cursoRepository, CategoriaRepository categoriaRepository) {
        Formacao formacao = formacaoRepository.getOne(idFormacao);
        formacao.setTitulo(titulo);
        formacao.setDescricao(descricao);

        List<Curso> cursoList = new ArrayList<>();

        for(Long idCurso : idCursos){
            Curso curso = cursoRepository.getOne(idCurso);
            cursoList.add(curso);
        }

        Categoria categoria = categoriaRepository.getOne(idCategoria);

        formacao.setCategoria(categoria);
        formacao.setCursoList(cursoList);

        return formacao;
    }
     */

    public Formacao atualizar(Long idFormacao, FormacaoRepository formacaoRepository, CursoRepository cursoRepository, CategoriaRepository categoriaRepository) {
        Formacao formacao = formacaoRepository.getOne(idFormacao);
        formacao.setTitulo(titulo);
        formacao.setDescricao(descricao);
        Curso curso = cursoRepository.getOne(idCurso);
        Categoria categoria = categoriaRepository.getOne(idCategoria);

        formacao.setCategoria(categoria);
        formacao.setCurso(curso);

        return formacao;
    }

}
