package br.com.easylearn.controller.form;

import br.com.easylearn.controller.dto.RespostaDto;
import br.com.easylearn.domain.Resposta;
import br.com.easylearn.domain.Topico;
import br.com.easylearn.domain.Usuario;
import br.com.easylearn.repository.RespostaRepository;
import br.com.easylearn.repository.TopicosRepository;
import br.com.easylearn.repository.UsuarioRepository;

import java.util.Optional;

public class RespostaForm {

    private String mensagem;
    private Long idTopico;
    private Long idUsuario;

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setIdTopico(Long idTopico) {
        this.idTopico = idTopico;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public RespostaDto save(UsuarioRepository usuarioRepository, TopicosRepository topicosRepository, RespostaRepository respostaRepository) {
        Optional<Topico> topicoById = topicosRepository.findById(idTopico);

        if (topicoById.isPresent()){
            Optional<Usuario> autorById = usuarioRepository.findById(idUsuario);

            if (autorById.isPresent()){
                Resposta resposta = new Resposta(topicoById.get(),autorById.get(),mensagem);
                Resposta save = respostaRepository.save(resposta);
                return new RespostaDto(save);
            }
        }
        return null;
    }
}
