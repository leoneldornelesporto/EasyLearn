package br.com.easylearn.controller;

import br.com.easylearn.config.mailsender.SendMailServiceImpl;
import br.com.easylearn.controller.dto.TutorDto;
import br.com.easylearn.controller.form.AtualizacaoTutorForm;
import br.com.easylearn.controller.form.TutorForm;
import br.com.easylearn.domain.Tutor;
import br.com.easylearn.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TutorController {

    private final TutorRepository tutorRepository;
    private final SendMailServiceImpl service;

    @Autowired
    public TutorController(TutorRepository tutorRepository, SendMailServiceImpl service) {
        this.tutorRepository = tutorRepository;
        this.service = service;
    }

    @GetMapping("v1/protectedT/tutor")
    @PreAuthorize("hasRole('TUTOR')")
    public ResponseEntity<? extends List<TutorDto>> findAllTutores(){
        List<TutorDto> tutorDtoList = TutorDto.converter(tutorRepository.findAll());
        if (tutorDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(tutorDtoList);
    }

    @PostMapping("v1/SalvarTutor")
    @Transactional
    public ResponseEntity<? extends TutorDto> saveTutor(@RequestBody TutorForm tutorForm, UriComponentsBuilder uriBuilder) throws MessagingException {
        Tutor tutor = tutorForm.save(tutorRepository);
        try {
            if (!tutor.equals(null)){
                URI uri = uriBuilder.path("/v1/tutor/{id}").buildAndExpand(tutor.getId()).toUri();
                //String link = "https://easylearn-app.herokuapp.com/ativarTutor/"+tutor.getUuid();
                //String link = "http://localhost:8080/ativarAluno/"+tutor.getUuid();
                //Mail email = new Mail(tutor.getEmail(),"Confirma????o de Conta","Por gentiliza acesse esse link " +"<a href='"+link+"'>aqui</a>");
                //service.sendMailWithAttachments(email);
                return ResponseEntity.created(uri).body(new TutorDto(tutor));
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("v1/protectedT/tutor/{idTutor}")
    @PreAuthorize("hasRole('TUTOR')")
    @Transactional
    public ResponseEntity<? extends TutorDto> atualizarTutor(@PathVariable Long idTutor, @RequestBody AtualizacaoTutorForm form) {
        Optional<Tutor> optional = tutorRepository.findById(idTutor);
        if (optional.isPresent()) {
            Tutor tutor = form.atualizar(idTutor, tutorRepository);
            return ResponseEntity.ok(new TutorDto(tutor));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("v1/protectedT/tutor/{idTutor}")
    @PreAuthorize("hasRole('TUTOR')")
    @Transactional
    public ResponseEntity<?> removerTutor(@PathVariable Long idTutor) {
        Optional<Tutor> optional = tutorRepository.findById(idTutor);
        if (optional.isPresent()) {
            tutorRepository.deleteById(idTutor);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("ativarTutor/{uuid}")
    @Transactional
    public ResponseEntity<? extends TutorDto> ativarTutor(@PathVariable String uuid) {
        Optional<Tutor> optional = tutorRepository.findByUuid(uuid);
        if (optional.isPresent()) {
            Tutor tutor = optional.get();
            tutor.setAtivo(Boolean.TRUE);
            return ResponseEntity.ok(new TutorDto(tutor));
        }
        return ResponseEntity.notFound().build();
    }
}