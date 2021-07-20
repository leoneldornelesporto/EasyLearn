package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.ReforcoDto;
import br.com.easylearn.controller.form.ReforcoForm;
import br.com.easylearn.domain.Reforco;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.ReforcoRepository;
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

@RestController
@RequestMapping("v1/reforco")
@PreAuthorize("hasRole('TUTOR')")
public class ReforcoController {

    private final TutorRepository tutorRepository;
    private final ReforcoRepository reforcoRepository;
    private final CursoRepository cursoRepository;

    @Autowired
    public ReforcoController(TutorRepository tutorRepository, ReforcoRepository reforcoRepository, CursoRepository cursoRepository) {
        this.tutorRepository = tutorRepository;
        this.reforcoRepository = reforcoRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    @Cacheable(value = "listaDeReforcos")
    public ResponseEntity<? extends List<ReforcoDto>> findAllReforcos(){
        List<ReforcoDto> reforcoDtos = ReforcoDto.converter(reforcoRepository.findAll());
        if (reforcoDtos.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(reforcoDtos);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeReforcos", allEntries = true)
    public ResponseEntity<? extends ReforcoDto> saveReforco(@RequestBody ReforcoForm reforcoForm, UriComponentsBuilder uriBuilder){
        Reforco reforco = reforcoForm.save(tutorRepository,reforcoRepository,cursoRepository);
        URI uri = uriBuilder.path("/v1/reforco/{id}").buildAndExpand(reforco.getId()).toUri();
        return ResponseEntity.created(uri).body(new ReforcoDto(reforco));
    }
}
