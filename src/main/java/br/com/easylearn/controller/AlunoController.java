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
        alunoRepository.deleteById(17L);
        alunoRepository.deleteById(18L);
        alunoRepository.deleteById(19L);
        AlunoForm professorForm = new AlunoForm("PauloAluno","Paulo Professor","","112355555588",
                "","paulo_aluno@outlook.com","12345","14-11-1969","Teste","","","","","","","https://i1.rgstatic.net/ii/profile.image/610962222628865-1522676153533_Q128/Paulo-Asconavieta.jpg","","");
        professorForm.save(alunoRepository);

        AlunoForm professorForm1 = new AlunoForm("MarciaAluna","Marcia Professora","","88877722241",
                "","marcia_aluna@outlook.com","12345","12-11-1979","","","","","","","","http://ubiq.inf.ufpel.edu.br/marciazg/lib/exe/fetch.php?media=mzg.jpg","","");

        professorForm1.save(alunoRepository);

        AlunoForm professorForm2 = new AlunoForm("MarlaAluna","Marla Professora"," Titular IFSUL","99988877355",
                "","marla_aluna@outlook.com","12345","12-12-1989","","","","","","","","http://www2.pelotas.ifsul.edu.br/bibdipec/images/foto_Cintia_Silva.jpg","","");

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
