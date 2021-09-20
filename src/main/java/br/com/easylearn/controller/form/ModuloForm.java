package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Aula;
import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Modulo;
import br.com.easylearn.repository.AulaRepository;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.ModuloRepository;

import java.util.ArrayList;
import java.util.List;

public class ModuloForm {

    private Integer indice;
    private String titulo;
    private String tituloSecundario;
    private Long idCurso;
    private List<Long> idAula;
    private List<String> subtitulo = new ArrayList<>();

    public Modulo save(ModuloRepository moduloRepository, CursoRepository cursoRepository, AulaRepository aulaRepository) {
        List<Aula> aulaList = new ArrayList<>();
        Curso curso = cursoRepository.getOne(idCurso);

        for (Long id:idAula){
            Aula aula = aulaRepository.getOne(id);
            aulaList.add(aula);
        }
        Modulo modulo = new Modulo(indice,titulo,tituloSecundario,curso,aulaList,subtitulo);
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

    public void setIdAula(List<Long> idAula) {
        this.idAula = idAula;
    }

    public void setSubtitulo(List<String> subtitulo) {
        this.subtitulo = subtitulo;
    }
}
