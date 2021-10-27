package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Modulo;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.ModuloRepository;

public class ModuloForm {

    private Integer indice;
    private String titulo;
    private String tituloSecundario;
    private Long idCurso;

    public Modulo save(ModuloRepository moduloRepository, CursoRepository cursoRepository) {
        Modulo modulo = new Modulo(indice,titulo,tituloSecundario);
        Curso curso = cursoRepository.getOne(idCurso);
        modulo.setCurso(curso);
        return moduloRepository.save(modulo);
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
