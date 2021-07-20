package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.AulaDto;
import br.com.easylearn.controller.form.AtualizacaoAulaForm;
import br.com.easylearn.controller.form.AulaForm;
import br.com.easylearn.domain.Aula;
import br.com.easylearn.repository.AulaRepository;
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

@RequestMapping("v1/aula")
@RestController
public class AulaController {

    private final AulaRepository aulaRepository;

    @Autowired
    public AulaController(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ALUNO') or hasRole('PROFESSOR')")
    @Cacheable(value = "listaDeAulas")
    public ResponseEntity<? extends List<AulaDto>> findAllAulas(){
        List<AulaDto> aulaDtoList = AulaDto.converter(aulaRepository.findAll());

        if (aulaDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(aulaDtoList);
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    @CacheEvict(value = "listaDeAulas", allEntries = true)
    public ResponseEntity<? extends AulaDto> saveAula(@RequestBody AulaForm aulaForm, UriComponentsBuilder uriBuilder){
        Aula aula  = aulaForm.save(aulaRepository);
        URI uri = uriBuilder.path("/v1/aula/{id}").buildAndExpand(aula.getId()).toUri();
        return ResponseEntity.created(uri).body(new AulaDto(aula));
    }

    @PutMapping("{idAula}")
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    @CacheEvict(value = "listaDeAulas", allEntries = true)
    public ResponseEntity<? extends AulaDto> atualizarAula(@PathVariable Long idAula, @RequestBody AtualizacaoAulaForm form) {
        Optional<Aula> optional = aulaRepository.findById(idAula);
        if (optional.isPresent()) {
            Aula aula = form.atualizar(idAula, aulaRepository);
            return ResponseEntity.ok(new AulaDto(aula));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{idAula}")
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    @CacheEvict(value = "listaDeAulas", allEntries = true)
    public ResponseEntity<?> removerAula(@PathVariable Long idAula) {
        Optional<Aula> optional = aulaRepository.findById(idAula);
        if (optional.isPresent()) {
            aulaRepository.deleteById(idAula);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
