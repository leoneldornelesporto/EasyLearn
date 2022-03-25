package br.com.easylearn.controller;

import br.com.easylearn.config.exception.ResourceBadRequestException;
import br.com.easylearn.config.exception.ResourceNotFoundException;
import br.com.easylearn.config.mailsender.SendMailServiceImpl;
import br.com.easylearn.controller.dto.AlunoDto;
import br.com.easylearn.controller.form.AlunoForm;
import br.com.easylearn.controller.form.AtualizacaoAlunoForm;
import br.com.easylearn.controller.form.ProfessorForm;
import br.com.easylearn.domain.Aluno;
import br.com.easylearn.domain.Mail;
import br.com.easylearn.repository.AlunoRepository;
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
public class AlunoController {

    private final AlunoRepository alunoRepository;
    private final SendMailServiceImpl service;

    @Autowired
    public AlunoController(AlunoRepository alunoRepository, SendMailServiceImpl service) {
        this.alunoRepository = alunoRepository;
        this.service = service;
    }

    @GetMapping("/cadastrarAlunos")
    public Boolean cadastrarUsuarios(){
        AlunoForm professorForm = new AlunoForm("Paulo","Professor Paulo","Professor Titular IFSUL","000.222.555-88",
                "professor-paulo.com","paulo_aluno@outlook.com","12345","14-11-1989","Teste","Teste","Teste","Teste","IFSUL","Professor Titular","Teste","https://i1.rgstatic.net/ii/profile.image/610962222628865-1522676153533_Q128/Paulo-Asconavieta.jpg","IFSUL","TSI");
        professorForm.save(alunoRepository);

        AlunoForm professorForm1 = new AlunoForm("Marcia","Professora Marcia","Professora Titular IFSUL","000.555.222-11",
                "professora-marcia.com","marcia_aluna@outlook.com","12345","12-11-1989","Teste","Teste","Teste","Teste","IFSUL","Professor Titular","Teste","http://ubiq.inf.ufpel.edu.br/marciazg/lib/exe/fetch.php?media=mzg.jpg","IFSUL","TSI");

        professorForm1.save(alunoRepository);

        AlunoForm professorForm2 = new AlunoForm("Marla","Professora Marla","Professora Titular IFSUL","111.555.222-22",
                "professora-marla.com","marla_aluna@outlook.com","12345","12-11-1989","Teste","Teste","Teste","Teste","IFSUL","Professor Titular","Teste","http://www2.pelotas.ifsul.edu.br/bibdipec/images/foto_Cintia_Silva.jpg","IFSUL","TSI");

        professorForm2.save(alunoRepository);

        return true;
    }

    @GetMapping("v1/protectedA/aluno")
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<? extends List<AlunoDto>> findAllAlunos(){
        List<AlunoDto> alunoDtoList = AlunoDto.converter(alunoRepository.findAll());
        if (alunoDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(alunoDtoList);
    }

    @PostMapping("v1/salvar/aluno")
    @Transactional
    public ResponseEntity<? extends AlunoDto> saveAluno(@RequestBody AlunoForm alunoForm, UriComponentsBuilder uriBuilder) {
        verifyAlunoExistsByCpfAndEmail(alunoForm.getCpf(),alunoForm.getEmail());
        Aluno aluno = alunoForm.save(alunoRepository);
        //String link = "https://easylearn-app.herokuapp.com/confirmar_email="+aluno.getId();
        //String link = "http://localhost:3000/confirmar_email="+aluno.getId();
        URI uri = uriBuilder.path("/v1/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
        //Mail email = new Mail(aluno.getEmail(),"Confirmação de Conta","Por gentiliza acesse esse link "+"<a href='"+link+"'>aqui</a>");
       //service.sendMailWithAttachments(email);

        return ResponseEntity.created(uri).body(new AlunoDto(aluno));
    }

    @PutMapping("v1/aluno/{idAluno}")
    @PreAuthorize("hasRole('ALUNO')")
    @Transactional
    public ResponseEntity<? extends AlunoDto> atualizarAluno(@PathVariable Long idAluno, @RequestBody AtualizacaoAlunoForm form){
        verifyAlunoExists(idAluno);
        Optional<Aluno> optional = alunoRepository.findById(idAluno);
        if (optional.isPresent()) {
            Aluno aluno = form.atualizar(idAluno,alunoRepository);
            return ResponseEntity.ok(new AlunoDto(aluno));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("v1/resetPassword/{email}")
    @Transactional
    public ResponseEntity<? extends AlunoDto> enviaEmailComLinkDeRedefinicaoDeSenha(@PathVariable String email) throws MessagingException {
        Optional<Aluno> byEmail = alunoRepository.findByEmail(email);

        if (byEmail.isPresent()) {
            //String link = "https://easylearn-app.herokuapp.com/resetPassword="+byEmail.get().getId()+"&email="+byEmail.get().getEmail();
            String link = "http://localhost:3000/resetPassword="+byEmail.get().getId()+"&email="+byEmail.get().getEmail();
            String assunto = "Olá "+byEmail.get().getNomeCompleto()+", Aparentemente, você pediu para alterar sua senha. Para fazer isso basta seguir o link: "+"<a href='"+link+"'>aqui</a>"+" Se não foi você, basta ignorar esse e-mail.";

            Mail mail = new Mail(byEmail.get().getEmail(),"RECUPERAR SENHA",assunto);
            service.sendMailWithAttachments(mail);
            return ResponseEntity.ok(new AlunoDto(byEmail.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("v1/resetPassword/{id}/{email}")
    @Transactional
    public ResponseEntity<? extends AlunoDto> redefinirSenha(@PathVariable Long id, @PathVariable String email, @RequestBody String senha){
        Optional<Aluno> aluno = alunoRepository.findByIdAndEmail(id,email);

        if (aluno.isPresent()){
            aluno.get().setSenha(senha);
            alunoRepository.save(aluno.get());
            return ResponseEntity.ok(AlunoDto.converter(aluno.get()));
        }
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("ativarAluno/uuid/{uuid}")
    @Transactional
    public ResponseEntity<? extends AlunoDto> ativarAlunoByUuid(@PathVariable String uuid) {
        Optional<Aluno> optional = alunoRepository.findByUuid(uuid);
        if (optional.isPresent()) {
            Aluno aluno = optional.get();
            aluno.setAtivo(Boolean.TRUE);
            return ResponseEntity.ok(new AlunoDto(aluno));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("ativarAluno/id/{id}")
    @Transactional
    public ResponseEntity<? extends AlunoDto> ativarAlunoById(@PathVariable Long id) {
        Optional<Aluno> optional = alunoRepository.findById(id);
        if (optional.isPresent()) {
            Aluno aluno = optional.get();
            aluno.setAtivo(Boolean.TRUE);
            return ResponseEntity.ok(new AlunoDto(aluno));
        }
        return ResponseEntity.notFound().build();
    }

    private void verifyAlunoExists(Long id){
        if(!alunoRepository.findById(id).isPresent()){
            throw new ResourceNotFoundException("Aluno not found for ID: "+id);
        }
    }

    private void verifyAlunoExistsByCpfAndEmail(String cpf, String email){
        if(alunoRepository.findByCpf(cpf).isPresent()){
            throw new ResourceBadRequestException("Aluno exists: cpf: "+cpf);
        }

        if(alunoRepository.findByEmail(email).isPresent()){
            throw new ResourceBadRequestException("Aluno exists: email: "+email);
        }
    }
}
