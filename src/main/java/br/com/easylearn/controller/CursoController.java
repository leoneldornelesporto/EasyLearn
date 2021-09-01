package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.CursoDto;
import br.com.easylearn.controller.form.AtualizacaoCursoForm;
import br.com.easylearn.controller.form.CursoForm;
import br.com.easylearn.domain.Curso;
import br.com.easylearn.repository.CategoriaRepository;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("v1/curso")
@RestController
public class CursoController {

    private final CursoRepository cursoRepository;
    private final ProfessorRepository professorRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CursoController(CursoRepository cursoRepository, ProfessorRepository professorRepository, CategoriaRepository categoriaRepository) {
        this.cursoRepository = cursoRepository;
        this.professorRepository = professorRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    @Cacheable(value = "listaDeCursos")
    public ResponseEntity<? extends List<CursoDto>> findAllCursos(){
        List<CursoDto> cursoDtoList = CursoDto.converter(cursoRepository.findAll());

        if (cursoDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(cursoDtoList);
    }

    @PostMapping
    @Transactional
    //@PreAuthorize("hasRole('PROFESSOR')")
    @CacheEvict(value = "listaDeCursos", allEntries = true)
    public ResponseEntity<? extends CursoDto> saveCurso(@RequestBody CursoForm cursoForm, UriComponentsBuilder uriBuilder){
        Curso curso  = cursoForm.save(cursoRepository,professorRepository,categoriaRepository);
        URI uri = uriBuilder.path("/v1/curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new CursoDto(curso));
    }

    @PutMapping("{idCurso}")
    @Transactional
    @CacheEvict(value = "listaDeCursos", allEntries = true)
    //@PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<? extends CursoDto> atualizarCurso(@PathVariable Long idCurso, @RequestBody AtualizacaoCursoForm form) {
        Optional<Curso> optional = cursoRepository.findById(idCurso);
        if (optional.isPresent()) {
            Curso curso = form.atualizar(idCurso, cursoRepository,categoriaRepository);
            return ResponseEntity.ok(new CursoDto(curso));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{idCurso}")
    @Transactional
    @CacheEvict(value = "listaDeCursos", allEntries = true)
    //@PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<?> removerCurso(@PathVariable Long idCurso) {
        Optional<Curso> optional = cursoRepository.findById(idCurso);
        if (optional.isPresent()) {
            cursoRepository.deleteById(idCurso);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
