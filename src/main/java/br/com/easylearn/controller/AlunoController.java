package br.com.easylearn.controller;

import br.com.easylearn.config.mailsender.SendMailServiceImpl;
import br.com.easylearn.controller.dto.AlunoDto;
import br.com.easylearn.controller.form.AlunoForm;
import br.com.easylearn.controller.form.AtualizacaoAlunoForm;
import br.com.easylearn.domain.Aluno;
import br.com.easylearn.domain.Mail;
import br.com.easylearn.repository.AlunoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "AlunoController")
public class AlunoController {

    private final AlunoRepository alunoRepository;
    private final SendMailServiceImpl service;

    @Autowired
    public AlunoController(AlunoRepository alunoRepository, SendMailServiceImpl service) {
        this.alunoRepository = alunoRepository;
        this.service = service;
    }

    @ApiOperation(value = "Retorna todos os alunos")
    @PreAuthorize("hasRole('ALUNO')")
    @GetMapping("v1/aluno")
    @Cacheable(value = "listaDeAlunos")
    public ResponseEntity<? extends List<AlunoDto>> findAllAlunos(){
        List<AlunoDto> alunoDtoList = AlunoDto.converter(alunoRepository.findAll());
        if (alunoDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(alunoDtoList);
    }

    @PostMapping("v1/SalvarAluno")
    @Transactional
    @CacheEvict(value = "listaDeAlunos", allEntries = true)
    public ResponseEntity<? extends AlunoDto> saveAluno(@RequestBody AlunoForm alunoForm, UriComponentsBuilder uriBuilder) throws MessagingException {
        Aluno aluno = alunoForm.save(alunoRepository);
        String link = "https://easylearn-app.herokuapp.com/ativar/"+aluno.getId();
        URI uri = uriBuilder.path("/v1/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
        Mail email = new Mail(aluno.getEmail(),"Confirmação de Conta","Por gentiliza acesse esse link " +
                "<a href='"+link+"'>aqui</a>");
        service.sendMailWithAttachments(email);

        return ResponseEntity.created(uri).body(new AlunoDto(aluno));
    }

    @PreAuthorize("hasRole('ALUNO')")
    @PutMapping("v1/aluno/{idAluno}")
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

    @GetMapping("ativarAluno/{idAluno}")
    @Transactional
    @CacheEvict(value = "listaDeAlunos", allEntries = true)
    public ResponseEntity<? extends AlunoDto> ativarAluno(@PathVariable Long idAluno) {
        Optional<Aluno> optional = alunoRepository.findById(idAluno);
        if (optional.isPresent()) {
            Aluno aluno = optional.get();
            aluno.setAtivo(Boolean.TRUE);
            return ResponseEntity.ok(new AlunoDto(aluno));
        }
        return ResponseEntity.notFound().build();
    }
}