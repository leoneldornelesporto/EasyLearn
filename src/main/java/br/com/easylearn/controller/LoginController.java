package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.LoginDto;
import br.com.easylearn.domain.Usuario;
import br.com.easylearn.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("v1/login")
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ResponseEntity<? extends LoginDto> login(@RequestHeader String usuario, @RequestHeader String senha){
        Optional<Usuario> user = usuarioRepository.findByEmailAndSenha(usuario,senha);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        } else{
            return ResponseEntity.ok(LoginDto.converterBase64(user,senha));
        }
    }
}
