package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.AlunoDto;
import br.com.easylearn.controller.form.AlunoForm;
import br.com.easylearn.controller.form.AtualizacaoAlunoForm;
import br.com.easylearn.domain.Aluno;
import br.com.easylearn.repository.AlunoRepository;
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
@RequestMapping("v1/aluno")
//@PreAuthorize("hasRole('ALUNO')")
public class AlunoController {

    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @GetMapping
    @Cacheable(value = "listaDeAlunos")
    public ResponseEntity<? extends List<AlunoDto>> findAllAlunos(){
        Aluno aluno = new Aluno();
        aluno.setAluno(Boolean.TRUE);
        aluno.setProfessor(Boolean.FALSE);
        aluno.setTutor(Boolean.FALSE);
        aluno.setNomeCompleto("Leonel");
        aluno.setSenha("12345");
        alunoRepository.save(aluno);

        List<AlunoDto> alunoDtoList = AlunoDto.converter(alunoRepository.findAll());
        if (alunoDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(alunoDtoList);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeAlunos", allEntries = true)
    public ResponseEntity<? extends AlunoDto> saveAluno(@RequestBody AlunoForm alunoForm, UriComponentsBuilder uriBuilder){
        Aluno aluno = alunoForm.save(alunoRepository);
        URI uri = uriBuilder.path("/v1/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new AlunoDto(aluno));
    }

    @PutMapping("{idAluno}")
    @Transactional
    @CacheEvict(value = "listaDeAlunos", allEntries = true)
    public ResponseEntity<? extends AlunoDto> atualizarAluno(@PathVariable Long idAluno, @RequestBody AtualizacaoAlunoForm form) {
        Optional<Aluno> optional = alunoRepository.findById(idAluno);
        if (optional.isPresent()) {
            Aluno aluno = form.atualizar(idAluno,alunoRepository);
            return ResponseEntity.ok(new AlunoDto(aluno));
        }
        return ResponseEntity.notFound().build();
    }
}
