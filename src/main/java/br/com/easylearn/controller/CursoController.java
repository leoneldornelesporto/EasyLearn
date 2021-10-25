package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.AulaDto;
import br.com.easylearn.controller.dto.CursoDto;
import br.com.easylearn.controller.dto.ModuloDto;
import br.com.easylearn.controller.form.AtualizacaoCursoForm;
import br.com.easylearn.controller.form.CursoForm;
import br.com.easylearn.domain.Curso;
import br.com.easylearn.repository.*;
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
public class CursoController {

    private final CursoRepository cursoRepository;
    private final ProfessorRepository professorRepository;
    private final CategoriaRepository categoriaRepository;
    private final ModuloRepository moduloRepository;
    private final FormacaoRepository formacaoRepository;

    @Autowired
    public CursoController(CursoRepository cursoRepository, ProfessorRepository professorRepository, CategoriaRepository categoriaRepository, ModuloRepository moduloRepository, FormacaoRepository formacaoRepository) {
        this.cursoRepository = cursoRepository;
        this.professorRepository = professorRepository;
        this.categoriaRepository = categoriaRepository;
        this.moduloRepository = moduloRepository;
        this.formacaoRepository = formacaoRepository;
    }

    @GetMapping("v1/curso")
    @Cacheable(value = "listaDeCursos")
    public ResponseEntity<? extends List<CursoDto>> findAllCursos(){
        List<CursoDto> cursoDtoList = CursoDto.converter(cursoRepository.findCursosByAtivoTrue(),moduloRepository);
        if (cursoDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(cursoDtoList);
    }

    @GetMapping("v1/curso/categoria/{idCategoria}")
    @Cacheable(value = "listaDeCursos")
    public ResponseEntity<? extends List<CursoDto>> findAllCursosByCategoria(@PathVariable Long idCategoria){
        List<CursoDto> cursoDtoList = CursoDto.converter(cursoRepository.findByCategoriaId(idCategoria));

        if (cursoDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(cursoDtoList);
    }

    @GetMapping("v1/curso/{id}")
    @Cacheable(value = "listaDeCursos")
    public ResponseEntity<? extends CursoDto> findCursosById(@PathVariable Long id){
        CursoDto curso = CursoDto.converter(cursoRepository.getById(id));

        if (curso == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(curso);
    }

    @GetMapping("v1/curso/nome/{nome}")
    @Cacheable(value = "listaDeCursos")
    public ResponseEntity<? extends List<CursoDto>> findCursosByNome(@PathVariable String nome){
        List<CursoDto> cursoDtoList = CursoDto.converter(cursoRepository.findByNomeContaining(nome));

        if (cursoDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(cursoDtoList);
    }

    @GetMapping("v1/curso/uuid/{uuid}")
    @Cacheable(value = "listaDeCursos")
    public ResponseEntity<? extends CursoDto> findCursosByUuid(@PathVariable String uuid){
        CursoDto curso = CursoDto.converter(cursoRepository.findByUuid(uuid),moduloRepository);

        if (curso == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(curso);
    }

    @GetMapping("v1/curso/uuid/{uuid}/aula/{idAula}")
    @Cacheable(value = "listaDeCursos")
    public ResponseEntity<? extends AulaDto> findAulaByUuidCurso(@PathVariable String uuid, @PathVariable Long idAula){
        CursoDto curso = CursoDto.converter(cursoRepository.findByUuid(uuid),moduloRepository);

        for(ModuloDto moduloDto:curso.getModuloDtoList()){
            for (AulaDto aulaDto:moduloDto.getAulaDto()){
                if (aulaDto.getId().equals(idAula)){
                    return ResponseEntity.ok(aulaDto);
                }
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("v1/protectedP/curso")
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    @CacheEvict(value = "listaDeCursos", allEntries = true)
    public ResponseEntity<? extends CursoDto> saveCurso(@RequestBody CursoForm cursoForm, UriComponentsBuilder uriBuilder){
        Curso curso  = cursoForm.save(cursoRepository,professorRepository,categoriaRepository,formacaoRepository);
        URI uri = uriBuilder.path("/v1/curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new CursoDto(curso, moduloRepository));
    }

    @PutMapping("v1/protectedP/curso/{idCurso}")
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    @CacheEvict(value = "listaDeCursos", allEntries = true)
    public ResponseEntity<? extends CursoDto> atualizarCurso(@PathVariable Long idCurso, @RequestBody AtualizacaoCursoForm form) {
        Optional<Curso> optional = cursoRepository.findById(idCurso);
        if (optional.isPresent()) {
            Curso curso = form.atualizar(idCurso, cursoRepository,categoriaRepository);
            return ResponseEntity.ok(new CursoDto(curso, moduloRepository));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("v1/protectedP/curso/{idCurso}")
    @Transactional
    @PreAuthorize("hasRole('PROFESSOR')")
    @CacheEvict(value = "listaDeCursos", allEntries = true)
    public ResponseEntity<?> removerCurso(@PathVariable Long idCurso) {
        Optional<Curso> optional = cursoRepository.findById(idCurso);
        if (optional.isPresent()) {
            cursoRepository.deleteById(idCurso);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
