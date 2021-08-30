package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.LoginDto;
import br.com.easylearn.domain.Usuario;
import br.com.easylearn.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("login")
@Api(value = "LoginController")
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/signin")
    public ResponseEntity<?> login(@RequestHeader String usuario, @RequestHeader String senha) {
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
}
