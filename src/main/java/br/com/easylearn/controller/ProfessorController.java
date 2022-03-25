package br.com.easylearn.controller;

import br.com.easylearn.config.mailsender.SendMailServiceImpl;
import br.com.easylearn.controller.dto.AlunoDto;
import br.com.easylearn.controller.dto.ProfessorDto;
import br.com.easylearn.controller.dto.TutorDto;
import br.com.easylearn.controller.form.AlunoForm;
import br.com.easylearn.controller.form.AtualizacaoAlunoForm;
import br.com.easylearn.controller.form.AtualizacaoProfessorForm;
import br.com.easylearn.controller.form.ProfessorForm;
import br.com.easylearn.domain.Aluno;
import br.com.easylearn.domain.Mail;
import br.com.easylearn.domain.Professor;
import br.com.easylearn.domain.Tutor;
import br.com.easylearn.repository.AlunoRepository;
import br.com.easylearn.repository.ProfessorRepository;
import br.com.easylearn.repository.TutorRepository;
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
public class ProfessorController {

    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;
    private final TutorRepository tutorRepository;
    private final SendMailServiceImpl service;

    @Autowired
    public ProfessorController(ProfessorRepository professorRepository, AlunoRepository alunoRepository, TutorRepository tutorRepository, SendMailServiceImpl service) {
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
        this.tutorRepository = tutorRepository;
        this.service = service;
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @GetMapping("v1/protectedP/professor")
    public ResponseEntity<? extends List<ProfessorDto>> findAllProfessores(){
        List<ProfessorDto> professorDtoList = ProfessorDto.converter(professorRepository.findAll());
        if (professorDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(professorDtoList);
    }

    @GetMapping("/cadastrarProfessores")
    public Boolean cadastrarUsuarios(){
        professorRepository.deleteById(8L);
        professorRepository.deleteById(9L);
        professorRepository.deleteById(10L);

        ProfessorForm professorForm = new ProfessorForm("Paulo","Professor Paulo","Professor Titular IFSUL","000.222.555-88",
                "professor-paulo.com","paulo_professor@outlook.com","12345","14-11-1989","Teste","Teste","Teste","Teste","IFSUL","Professor Titular","Teste","https://i1.rgstatic.net/ii/profile.image/610962222628865-1522676153533_Q128/Paulo-Asconavieta.jpg","IFSUL","TSI");
        professorForm.save(professorRepository);

        ProfessorForm professorForm1 = new ProfessorForm("Marcia","Professora Marcia","Professora Titular IFSUL","000.555.222-11",
                "professora-marcia.com","marcia_professor@outlook.com","12345","12-11-1989","Teste","Teste","Teste","Teste","IFSUL","Professor Titular","Teste","http://ubiq.inf.ufpel.edu.br/marciazg/lib/exe/fetch.php?media=mzg.jpg","IFSUL","TSI");

        professorForm1.save(professorRepository);

        ProfessorForm professorForm2 = new ProfessorForm("Marla","Professora Marla","Professora Titular IFSUL","111.555.222-22",
                "professora-marla.com","marla_professor@outlook.com","12345","12-11-1989","Teste","Teste","Teste","Teste","IFSUL","Professor Titular","Teste","http://www2.pelotas.ifsul.edu.br/bibdipec/images/foto_Cintia_Silva.jpg","IFSUL","TSI");

        professorForm2.save(professorRepository);

        return true;
    }

    @PostMapping("v1/salvar/professor")
    @Transactional
    public ResponseEntity<? extends ProfessorDto> saveProfessor(@RequestBody ProfessorForm professorForm, UriComponentsBuilder uriBuilder) throws MessagingException {
        Professor professor = professorForm.save(professorRepository);
        try {
            if (!professor.equals(null)){
                URI uri = uriBuilder.path("/v1/professor/{id}").buildAndExpand(professor.getId()).toUri();
                //String link = "https://easylearn-app.herokuapp.com/ativarProfessor/"+professor.getUuid();
                //String link = "http://localhost:8080/ativarAluno/"+professor.getUuid();
                //Mail email = new Mail(professor.getEmail(),"Confirmação de Conta","Por gentiliza acesse esse link " +"<a href='"+link+"'>aqui</a>");
                //service.sendMailWithAttachments(email);
                return ResponseEntity.created(uri).body(new ProfessorDto(professor));
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @PutMapping("v1/protectedP/professor/{idProfessor}")
    @Transactional
    public ResponseEntity<? extends ProfessorDto> atualizarProfessor(@PathVariable Long idProfessor, @RequestBody AtualizacaoProfessorForm form) {
        Optional<Professor> optional = professorRepository.findById(idProfessor);
        if (optional.isPresent()) {
            Professor professor = form.atualizar(idProfessor,professorRepository);
            return ResponseEntity.ok(new ProfessorDto(professor));
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @DeleteMapping("v1/protectedP/professor/{idProfessor}")
    @Transactional
    public ResponseEntity<?> removerProfessor(@PathVariable Long idProfessor) {
        Optional<Professor> optional = professorRepository.findById(idProfessor);
        if (optional.isPresent()) {
            professorRepository.deleteById(idProfessor);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping("v1/protectedP/professor/salvar/aluno")
    @Transactional
    public ResponseEntity<? extends AlunoDto> saveAluno(@RequestBody AlunoForm alunoForm, UriComponentsBuilder uriBuilder){
        Aluno aluno = alunoForm.save(alunoRepository);
        URI uri = uriBuilder.path("/v1/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new AlunoDto(aluno));
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @DeleteMapping("v1/protectedP/professor/deletar/aluno/{idAluno}")
    @Transactional
    public ResponseEntity<?> removerAluno(@PathVariable Long idAluno) {
        Optional<Aluno> optional = alunoRepository.findById(idAluno);
        if (optional.isPresent()) {
            alunoRepository.deleteById(idAluno);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @DeleteMapping("v1/protectedP/professor/deletar/tutor/{idTutor}")
    @Transactional
    public ResponseEntity<?> removerTutor(@PathVariable Long idTutor) {
        Optional<Tutor> byIdTutor = tutorRepository.findById(idTutor);
        if (byIdTutor.isPresent()) {
            tutorRepository.deleteById(idTutor);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("ativarProfessor/{uuid}")
    @Transactional
    public ResponseEntity<? extends ProfessorDto> ativarProfessor(@PathVariable String uuid) {
        Optional<Professor> optional = professorRepository.findByUuid(uuid);
        if (optional.isPresent()) {
            Professor professor = optional.get();
            professor.setAtivo(Boolean.TRUE);
            return ResponseEntity.ok(new ProfessorDto(professor));
        }
        return ResponseEntity.notFound().build();
    }
}
