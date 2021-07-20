package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Ocorrencia;
import br.com.easylearn.domain.Usuario;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.OcorrenciaRepository;
import br.com.easylearn.repository.UsuarioRepository;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;

public class OcorrenciaForm {

    private Long idUsuario;
    private Long idCurso;
    private String descricao;
    private LocalDateTime date;

    public Ocorrencia save(OcorrenciaRepository ocorrenciaRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        Usuario usuario = usuarioRepository.getOne(idUsuario);
        Curso curso = cursoRepository.getOne(idCurso);
        Ocorrencia ocorrencia = new Ocorrencia(usuario,curso,descricao, date);
        return ocorrenciaRepository.save(ocorrencia);
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
