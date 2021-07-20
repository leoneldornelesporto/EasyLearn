package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.TutorDto;
import br.com.easylearn.controller.form.AtualizacaoTutorForm;
import br.com.easylearn.controller.form.TutorForm;
import br.com.easylearn.domain.Tutor;
import br.com.easylearn.repository.TutorRepository;
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

@RestController
@RequestMapping("v1/tutor")
@PreAuthorize("hasRole('TUTOR')")
public class TutorController {

    private final TutorRepository tutorRepository;

    @Autowired
    public TutorController(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @GetMapping
    @Cacheable(value = "listaDeTutores")
    public ResponseEntity<? extends List<TutorDto>> findAllTutores(){
        List<TutorDto> tutorDtoList = TutorDto.converter(tutorRepository.findAll());
        if (tutorDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(tutorDtoList);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeTutores", allEntries = true)
    public ResponseEntity<? extends TutorDto> saveTutor(@RequestBody TutorForm tutorForm, UriComponentsBuilder uriBuilder){
        Tutor tutor = tutorForm.save(tutorRepository);
        URI uri = uriBuilder.path("/v1/tutor/{id}").buildAndExpand(tutor.getId()).toUri();
        return ResponseEntity.created(uri).body(new TutorDto(tutor));
    }

    @PutMapping("{idTutor}")
    @Transactional
    @CacheEvict(value = "listaDeTutores", allEntries = true)
    public ResponseEntity<? extends TutorDto> atualizarTutor(@PathVariable Long idTutor, @RequestBody AtualizacaoTutorForm form) {
        Optional<Tutor> optional = tutorRepository.findById(idTutor);
        if (optional.isPresent()) {
            Tutor tutor = form.atualizar(idTutor, tutorRepository);
            return ResponseEntity.ok(new TutorDto(tutor));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{idTutor}")
    @Transactional
    @CacheEvict(value = "listaDeTutores", allEntries = true)
    public ResponseEntity<?> removerTutor(@PathVariable Long idTutor) {
        Optional<Tutor> optional = tutorRepository.findById(idTutor);
        if (optional.isPresent()) {
            tutorRepository.deleteById(idTutor);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

