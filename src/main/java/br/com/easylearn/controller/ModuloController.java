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
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("v1/modulo")
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

    @GetMapping
    @PreAuthorize("hasRole('ALUNO') or hasRole('PROFESSOR')")
    @Cacheable(value = "listaDeModulos")
    public ResponseEntity<? extends List<ModuloDto>> findAllModulos(@RequestParam(required = false) Long idCurso){

        if(idCurso == null){
            List<ModuloDto> moduloDtoList = ModuloDto.converter(moduloRepository.findAll());

            if (moduloDtoList.isEmpty())
                return ResponseEntity.notFound().build();
            else
                return ResponseEntity.ok(moduloDtoList);
        } else {
            List<ModuloDto> moduloDtoList = ModuloDto.converter(moduloRepository.findAllByCursoId(idCurso));

            if (moduloDtoList.isEmpty())
                return ResponseEntity.notFound().build();
            else
                return ResponseEntity.ok(moduloDtoList);
        }
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    @CacheEvict(value = "listaDeModulos", allEntries = true)
    public ResponseEntity<? extends ModuloDto> saveModulo(@RequestBody ModuloForm moduloForm, UriComponentsBuilder uriBuilder){
        Modulo modulo  = moduloForm.save(moduloRepository,cursoRepository,aulaRepository);
        URI uri = uriBuilder.path("/v1/modulo/{id}").buildAndExpand(modulo.getId()).toUri();
        return ResponseEntity.created(uri).body(new ModuloDto(modulo));
    }

    @PutMapping("{idModulo}")
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

    @DeleteMapping("{idModulo}")
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
