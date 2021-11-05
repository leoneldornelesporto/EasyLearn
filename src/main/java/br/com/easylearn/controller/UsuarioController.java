package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.UsuarioDto;
import br.com.easylearn.domain.Usuario;
import br.com.easylearn.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping("/user")
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/{nomeCompleto}")
    public ResponseEntity<? extends UsuarioDto> findUsuarioByName(@PathVariable String nomeCompleto){
        Optional<Usuario> byNomeCompletoContaining = usuarioRepository.findByNomeCompletoContaining(nomeCompleto);

        if (byNomeCompletoContaining.isPresent()){
            return ResponseEntity.ok(new UsuarioDto(byNomeCompletoContaining.get()));
        }

        return ResponseEntity.notFound().build();
    }
}
