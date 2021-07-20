package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.AlunoDto;
import br.com.easylearn.controller.dto.ProfessorDto;
import br.com.easylearn.controller.form.AlunoForm;
import br.com.easylearn.controller.form.AtualizacaoAlunoForm;
import br.com.easylearn.controller.form.AtualizacaoProfessorForm;
import br.com.easylearn.controller.form.ProfessorForm;
import br.com.easylearn.domain.Aluno;
import br.com.easylearn.domain.Professor;
import br.com.easylearn.repository.AlunoRepository;
import br.com.easylearn.repository.ProfessorRepository;
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
@RequestMapping("v1/professor")
@PreAuthorize("hasRole('PROFESSOR')")
public class ProfessorController {

    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;

    @Autowired
    public ProfessorController(ProfessorRepository professorRepository, AlunoRepository alunoRepository) {
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }

    @GetMapping
    @Cacheable(value = "listaDeProfessores")
    public ResponseEntity<? extends List<ProfessorDto>> findAllProfessores(){
        List<ProfessorDto> professorDtoList = ProfessorDto.converter(professorRepository.findAll());
        if (professorDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(professorDtoList);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeProfessores", allEntries = true)
    public ResponseEntity<? extends ProfessorDto> saveProfessor(@RequestBody ProfessorForm professorForm, UriComponentsBuilder uriBuilder){
        Professor professor = professorForm.save(professorRepository);
        URI uri = uriBuilder.path("/v1/professor/{id}").buildAndExpand(professor.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProfessorDto(professor));
    }

    @PutMapping("{idProfessor}")
    @Transactional
    @CacheEvict(value = "listaDeProfessores", allEntries = true)
    public ResponseEntity<? extends ProfessorDto> atualizarProfessor(@PathVariable Long idProfessor, @RequestBody AtualizacaoProfessorForm form) {
        Optional<Professor> optional = professorRepository.findById(idProfessor);
        if (optional.isPresent()) {
            Professor professor = form.atualizar(idProfessor,professorRepository);
            return ResponseEntity.ok(new ProfessorDto(professor));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("professor/{idProfessor}")
    @Transactional
    @CacheEvict(value = "listaDeTutores", allEntries = true)
    public ResponseEntity<?> removerProfessor(@PathVariable Long idProfessor) {
        Optional<Professor> optional = professorRepository.findById(idProfessor);
        if (optional.isPresent()) {
            professorRepository.deleteById(idProfessor);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("aluno")
    @Transactional
    @CacheEvict(value = "listaDeAlunos", allEntries = true)
    public ResponseEntity<? extends AlunoDto> saveAluno(@RequestBody AlunoForm alunoForm, UriComponentsBuilder uriBuilder){
        Aluno aluno = alunoForm.save(alunoRepository);
        URI uri = uriBuilder.path("/v1/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new AlunoDto(aluno));
    }

    @DeleteMapping("aluno/{idAluno}")
    @Transactional
    @CacheEvict(value = "listaDeTutores", allEntries = true)
    public ResponseEntity<?> removerAluno(@PathVariable Long idAluno) {
        Optional<Aluno> optional = alunoRepository.findById(idAluno);
        if (optional.isPresent()) {
            alunoRepository.deleteById(idAluno);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
