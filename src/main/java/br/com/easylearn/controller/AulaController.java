package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.AulaDto;
import br.com.easylearn.controller.form.AtualizacaoAulaForm;
import br.com.easylearn.controller.form.AulaForm;
import br.com.easylearn.domain.Aula;
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
public class AulaController {

    private final AulaRepository aulaRepository;
    private final CursoRepository cursoRepository;
    private final ModuloRepository moduloRepository;

    @Autowired
    public AulaController(AulaRepository aulaRepository, CursoRepository cursoRepository, ModuloRepository moduloRepository) {
        this.aulaRepository = aulaRepository;
        this.cursoRepository = cursoRepository;
        this.moduloRepository = moduloRepository;
    }

    @GetMapping("v1/protectedP/aulas")
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<? extends List<AulaDto>> returnAllAulas(){
        List<AulaDto> converter = AulaDto.converter(aulaRepository.findAll());
        if (converter.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(converter);
    }

    @GetMapping("v1/aulasS/modulo/{id}")
    @Transactional
    public ResponseEntity<Long> retornaIdDoModuloQueAAulaFazParte(@PathVariable Long id){
        Optional<Aula> byId = aulaRepository.findById(id);
        if (byId.isPresent())
            return ResponseEntity.ok(byId.get().getModulo().getId());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("v1/protectedA/aulas/{id}")
    @Transactional
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<? extends AulaDto> getAulaByIdAula(@PathVariable Long id) {
        Optional<Aula> byId = aulaRepository.findById(id);

            if (byId.isPresent()) {
                return ResponseEntity.ok(new AulaDto(byId.get()));
            }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("v1/protectedA/aulas/{id}/{uuid}")
    @Transactional
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<? extends AulaDto> getAulaByIdAulaAndUuidCurso(@PathVariable Long id, @PathVariable String uuid) {
        Optional<Aula> byId = aulaRepository.findByIdAndModuloCursoUuid(id,uuid);

        if (byId.isPresent()) {
            return ResponseEntity.ok(new AulaDto(byId.get()));
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping("v1/protectedP/aulas")
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<? extends AulaDto> saveAula(@RequestBody AulaForm aulaForm, UriComponentsBuilder uriBuilder){
        Aula aula  = aulaForm.save(aulaRepository,moduloRepository);
        URI uri = uriBuilder.path("/v1/aula/{id}").buildAndExpand(aula.getId()).toUri();
        return ResponseEntity.created(uri).body(new AulaDto(aula));
    }

    @PutMapping("v1/protectedP/aulas/{idAula}")
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<? extends AulaDto> atualizarAula(@PathVariable Long idAula, @RequestBody AtualizacaoAulaForm form) {
        Optional<Aula> optional = aulaRepository.findById(idAula);
        if (optional.isPresent()) {
            Aula aula = form.atualizar(idAula,aulaRepository,moduloRepository);
            return ResponseEntity.ok(new AulaDto(aula));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("v1/protectedP/aulas/{idAula}")
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<?> removerAula(@PathVariable Long idAula) {
        Optional<Aula> optional = aulaRepository.findById(idAula);
        if (optional.isPresent()) {
            aulaRepository.deleteById(idAula);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
