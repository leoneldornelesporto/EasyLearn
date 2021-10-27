package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Modulo;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.ModuloRepository;

public class AtualizacaoModuloForm {

    private Integer indice;
    private String titulo;
    private String tituloSecundario;
    private Long idCurso;

    public Modulo atualizar(Long idModulo, ModuloRepository moduloRepository, CursoRepository cursoRepository) {
        Modulo modulo = moduloRepository.getOne(idModulo);
        modulo.setIndice(indice);
        modulo.setTitulo(titulo);
        modulo.setTituloSecundario(tituloSecundario);
        Curso curso = cursoRepository.getOne(idCurso);
        modulo.setCurso(curso);

        return modulo;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTituloSecundario(String tituloSecundario) {
        this.tituloSecundario = tituloSecundario;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }
}
