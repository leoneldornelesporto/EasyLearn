package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.ModuloDto;
import br.com.easylearn.controller.form.AtualizacaoModuloForm;
import br.com.easylearn.controller.form.ModuloForm;
import br.com.easylearn.domain.Modulo;
import br.com.easylearn.repository.AulaRepository;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
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
    public ResponseEntity<? extends ModuloDto> saveModulo(@RequestBody ModuloForm moduloForm, UriComponentsBuilder uriBuilder){
        Modulo modulo  = moduloForm.save(moduloRepository,cursoRepository);
        URI uri = uriBuilder.path("/v1/modulo/{id}").buildAndExpand(modulo.getId()).toUri();
        return ResponseEntity.created(uri).body(new ModuloDto(modulo));
    }

    @GetMapping("v1/protectedA/modulo/{idModulo}")
    @Transactional
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<? extends ModuloDto> findModuloById(@PathVariable Long idModulo) {
        Optional<Modulo> optional = moduloRepository.findById(idModulo);

        if (optional.isPresent()) {
            return ResponseEntity.ok(new ModuloDto(optional.get(),aulaRepository));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("v1/protectedA/modulo/idCurso/{idCurso}")
    @Transactional
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<? extends List<ModuloDto>> findAllModuloByIdCurso(@PathVariable Long idCurso) {
        List<Modulo> allByCursoId = moduloRepository.findAllByCursoId(idCurso);

        if (!allByCursoId.isEmpty()) {
            return ResponseEntity.ok(ModuloDto.converter(allByCursoId,aulaRepository));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("v1/modulo")
    @Transactional
    public ResponseEntity<? extends List<ModuloDto>> findAllModulos() {
        List<Modulo> optional = moduloRepository.findAll();
        if (!optional.isEmpty()) {
            return ResponseEntity.ok(ModuloDto.converter(optional,aulaRepository));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("v1/protectedP/modulo/{idModulo}")
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<? extends ModuloDto> atualizarModulo(@PathVariable Long idModulo, @RequestBody AtualizacaoModuloForm form) {
        Optional<Modulo> optional = moduloRepository.findById(idModulo);
        if (optional.isPresent()) {
            Modulo modulo = form.atualizar(idModulo, moduloRepository,cursoRepository);
            return ResponseEntity.ok(new ModuloDto(modulo,aulaRepository));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("v1/protectedP/modulo/{idModulo}")
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<?> removerModulo(@PathVariable Long idModulo) {
        Optional<Modulo> optional = moduloRepository.findById(idModulo);
        if (optional.isPresent()) {
            moduloRepository.deleteById(idModulo);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
