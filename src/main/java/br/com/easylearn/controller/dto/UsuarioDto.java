package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Usuario;
import org.springframework.data.domain.Page;

public class UsuarioDto {
    private String nome;
    private String email;

    public UsuarioDto(Usuario usuario) {
        this.nome = usuario.getNomeCompleto();
        this.email = usuario.getEmail();
    }

    public static Page<UsuarioDto> converter(Page<Usuario> usuarios) {
        return usuarios.map(UsuarioDto::new);
    }

    public static UsuarioDto converter(Usuario usuario) {
        return new UsuarioDto(usuario);
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
