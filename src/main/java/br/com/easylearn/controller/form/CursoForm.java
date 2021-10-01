package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Categoria;
import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Professor;
import br.com.easylearn.repository.CategoriaRepository;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.ProfessorRepository;

public class CursoForm {

    private Long idProfessor;
    private String nome;
    private String descricao;
    private Integer cargahoraria;
    private Long categoriaId;
    private String imagemIcon;
    private Boolean ativo;

    public void setIdProfessor(Long idProfessor) {
        this.idProfessor = idProfessor;
    }

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

    public Curso save(CursoRepository cursoRepository, ProfessorRepository professorRepository, CategoriaRepository categoriaRepository) {
        Professor professor = professorRepository.getOne(idProfessor);
        Categoria categoria = categoriaRepository.getOne(categoriaId);
        Curso curso = new Curso(cargahoraria, descricao, nome, imagemIcon, professor, categoria, ativo);
        return cursoRepository.save(curso);
    }
}
