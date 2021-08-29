package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.LoginDto;
import br.com.easylearn.domain.Usuario;
import br.com.easylearn.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("login")
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @GetMapping
    public ResponseEntity<? extends LoginDto> login(@RequestHeader String usuario, @RequestHeader String senha) {
        List<Usuario> usuarioList = usuarioRepository.findAll();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        for (Usuario user : usuarioList) {
            if (user.getEmail().equals(usuario)) {
                if(encoder.matches(senha, user.getPassword())){
                    Optional<Usuario> byEmailAndSenha = usuarioRepository.findByEmailAndSenha(usuario, user.getSenha());
                        return ResponseEntity.ok(LoginDto.converterBase64(byEmailAndSenha,senha));
                }
            }
        }
        return ResponseEntity.notFound().build();
    }


    /*
    @GetMapping
    public ResponseEntity<? extends LoginDto> login(@RequestHeader String usuario, @RequestHeader String senha) {
        Optional<Usuario> byEmailAndSenha = usuarioRepository.findByEmailAndSenha(usuario, senha);

        if (byEmailAndSenha.isPresent()){
            return ResponseEntity.ok(LoginDto.converterBase64(byEmailAndSenha,senha));
        }

        return ResponseEntity.notFound().build();
    }
     */
}
