package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.MinistraDto;
import br.com.easylearn.controller.form.MinistraForm;
import br.com.easylearn.domain.Ministra;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.MinistraRepository;
import br.com.easylearn.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/ministra")
@PreAuthorize("hasRole('PROFESSOR')")
public class MinistraController {

    private final MinistraRepository ministraRepository;
    private final ProfessorRepository professorRepository;
    private final CursoRepository cursoRepository;

    @Autowired
    public MinistraController(MinistraRepository ministraRepository, ProfessorRepository professorRepository, CursoRepository cursoRepository) {
        this.ministraRepository = ministraRepository;
        this.professorRepository = professorRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public ResponseEntity<? extends List<MinistraDto>> findAllMinistra(){
        List<MinistraDto> ministraDtos = MinistraDto.converter(ministraRepository.findAll());
        if (ministraDtos.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(ministraDtos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<? extends MinistraDto> saveMinistra(@RequestBody MinistraForm ministraForm, UriComponentsBuilder uriBuilder){
        Ministra ministra = ministraForm.save(ministraRepository,professorRepository,cursoRepository);
        URI uri = uriBuilder.path("/v1/ministra/{id}").buildAndExpand(ministra.getId()).toUri();
        return ResponseEntity.created(uri).body(new MinistraDto(ministra));
    }
}
