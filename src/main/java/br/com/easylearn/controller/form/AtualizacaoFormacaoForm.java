package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Formacao;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.FormacaoRepository;

import java.util.ArrayList;
import java.util.List;

public class AtualizacaoFormacaoForm {

    private List<Long> idCursos;
    private String titulo;
    private String descricao;

    public void setIdCursos(List<Long> idCursos) {
        this.idCursos = idCursos;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Formacao atualizar(Long idFormacao, FormacaoRepository formacaoRepository, CursoRepository cursoRepository) {
        Formacao formacao = formacaoRepository.getOne(idFormacao);
        formacao.setTitulo(titulo);
        formacao.setDescricao(descricao);

        List<Curso> cursoList = new ArrayList<>();

        for(Long idCurso : idCursos){
            Curso curso = cursoRepository.getOne(idCurso);
            cursoList.add(curso);
        }
        formacao.setCursoList(cursoList);

        return formacao;
    }
}
