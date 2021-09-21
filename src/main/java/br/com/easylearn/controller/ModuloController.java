package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.ModuloDto;
import br.com.easylearn.controller.form.AtualizacaoModuloForm;
import br.com.easylearn.controller.form.ModuloForm;
import br.com.easylearn.domain.Modulo;
import br.com.easylearn.repository.AulaRepository;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ModuloController {

    private final ModuloRepository moduloRepository;
    private final CursoRepository cursoRepository;
    private final AulaRepository aulaRepository;

    @Autowired
    public ModuloController(ModuloRepository moduloRepository, CursoRepository cursoRepository, AulaRepository aulaRepository) {
        this.moduloRepository = moduloRepository;
        this.cursoRepository = cursoRepository;
        this.aulaRepository = aulaRepository;
    }

    @PostMapping("v1/protectedP/modulo")
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    @CacheEvict(value = "listaDeModulos", allEntries = true)
    public ResponseEntity<? extends ModuloDto> saveModulo(@RequestBody ModuloForm moduloForm, UriComponentsBuilder uriBuilder){
        Modulo modulo  = moduloForm.save(moduloRepository,cursoRepository,aulaRepository);
        URI uri = uriBuilder.path("/v1/modulo/{id}").buildAndExpand(modulo.getId()).toUri();
        return ResponseEntity.created(uri).body(new ModuloDto(modulo));
    }

    @GetMapping("v1/protectedP/modulo/{idModulo}")
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    @CacheEvict(value = "listaDeModulos", allEntries = true)
    public ResponseEntity<? extends ModuloDto> findModuloById(@PathVariable Long idModulo) {
        Optional<Modulo> optional = moduloRepository.findById(idModulo);
        if (optional.isPresent()) {
            //return ResponseEntity.ok(new ModuloDto(optional.get()));
            //return Response.status(200).entity(new ModuloDto(optional.get())).header("Access-Control-Allow-Origin", "*").build();
            return ResponseEntity.ok(new ModuloDto(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("v1/protectedP/modulo/{idModulo}")
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    @CacheEvict(value = "listaDeModulos", allEntries = true)
    public ResponseEntity<? extends ModuloDto> atualizarModulo(@PathVariable Long idModulo, @RequestBody AtualizacaoModuloForm form) {
        Optional<Modulo> optional = moduloRepository.findById(idModulo);
        if (optional.isPresent()) {
            Modulo modulo = form.atualizar(idModulo, moduloRepository,aulaRepository);
            return ResponseEntity.ok(new ModuloDto(modulo));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("v1/protectedP/modulo/{idModulo}")
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    @CacheEvict(value = "listaDeModulos", allEntries = true)
    public ResponseEntity<?> removerModulo(@PathVariable Long idModulo) {
        Optional<Modulo> optional = moduloRepository.findById(idModulo);
        if (optional.isPresent()) {
            moduloRepository.deleteById(idModulo);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
