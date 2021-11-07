package br.com.easylearn.controller.form;

import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Topico;
import br.com.easylearn.domain.Usuario;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.UsuarioRepository;

public class TopicoForm {

    private String titulo;
    private String mensagem;
    private Long idCurso;
    private Long idUsuario;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Topico converter(CursoRepository cursoRepository, UsuarioRepository usuarioRepository) {
        Curso curso = cursoRepository.getOne(idCurso);
        Usuario usuario = usuarioRepository.getOne(idUsuario);
        return new Topico(titulo, mensagem, curso, usuario);
    }
}
