package br.com.easylearn.controller;

import br.com.easylearn.config.mailsender.SendMailServiceImpl;
import br.com.easylearn.controller.dto.AlunoDto;
import br.com.easylearn.controller.form.AlunoForm;
import br.com.easylearn.controller.form.AtualizacaoAlunoForm;
import br.com.easylearn.domain.Aluno;
import br.com.easylearn.domain.Mail;
import br.com.easylearn.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class AlunoController {

    private final AlunoRepository alunoRepository;
    private final SendMailServiceImpl service;

    @Autowired
    public AlunoController(AlunoRepository alunoRepository, SendMailServiceImpl service) {
        this.alunoRepository = alunoRepository;
        this.service = service;
    }

    @GetMapping("v1/protectedA/aluno")
    @PreAuthorize("hasRole('ALUNO')")
    @Cacheable(value = "listaDeAlunos")
    public ResponseEntity<? extends List<AlunoDto>> findAllAlunos(){
        List<AlunoDto> alunoDtoList = AlunoDto.converter(alunoRepository.findAll());
        if (alunoDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(alunoDtoList);
    }

    @PostMapping("v1/salvar/aluno")
    @Transactional
    @CacheEvict(value = "listaDeAlunos", allEntries = true)
    public ResponseEntity<? extends AlunoDto> saveAluno(@RequestBody AlunoForm alunoForm, UriComponentsBuilder uriBuilder) throws MessagingException {
        Aluno aluno = alunoForm.save(alunoRepository);
        //String link = "https://easylearn-app.herokuapp.com/ativarAluno/"+aluno.getUuid();
        String link = "http://localhost:8080/ativarAluno/"+aluno.getUuid();
        URI uri = uriBuilder.path("/v1/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
        Mail email = new Mail(aluno.getEmail(),"Confirmação de Conta","Por gentiliza acesse esse link " +
                "<a href='"+link+"'>aqui</a>");
        service.sendMailWithAttachments(email);

        return ResponseEntity.created(uri).body(new AlunoDto(aluno));
    }

    @PutMapping("v1/protectedA/aluno/{idAluno}")
    @PreAuthorize("hasRole('ALUNO')")
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

    @GetMapping("ativarAluno/{uuid}")
    @Transactional
    @CacheEvict(value = "listaDeAlunos", allEntries = true)
    public ResponseEntity<? extends AlunoDto> ativarAluno(@PathVariable String uuid) {
        Optional<Aluno> optional = alunoRepository.findByUuid(uuid);
        if (optional.isPresent()) {
            Aluno aluno = optional.get();
            aluno.setAtivo(Boolean.TRUE);
            return ResponseEntity.ok(new AlunoDto(aluno));
        }
        return ResponseEntity.notFound().build();
    }
}