package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.CursoDto;
import br.com.easylearn.controller.dto.MatriculasDto;
import br.com.easylearn.controller.form.MatriculaForm;
import br.com.easylearn.domain.AssistirAula;
import br.com.easylearn.domain.Aula;
import br.com.easylearn.domain.Matricula;
import br.com.easylearn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/protectedA/matricula")
@PreAuthorize("hasRole('ALUNO')")
public class MatriculaController {

    private final MatriculaRepository matriculaRepository;
    private final ModuloRepository moduloRepository;
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;
    private final AssistirAulaRepository assistirAulaRepository;
    private final AulaRepository aulaRepository;

    @Autowired
    public MatriculaController(MatriculaRepository matriculaRepository, ModuloRepository moduloRepository, AlunoRepository alunoRepository, CursoRepository cursoRepository, AssistirAulaRepository assistirAulaRepository, AulaRepository aulaRepository) {
        this.matriculaRepository = matriculaRepository;
        this.moduloRepository = moduloRepository;
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
        this.assistirAulaRepository = assistirAulaRepository;
        this.aulaRepository = aulaRepository;
    }

    @GetMapping
    @Cacheable(value = "listaDeMatriculas")
    public ResponseEntity<? extends List<MatriculasDto>> findAllMatriculas(){
        List<MatriculasDto> matriculasDtos = MatriculasDto.converter(matriculaRepository.findAll());
        if (matriculasDtos.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(matriculasDtos);
    }

    @GetMapping("/verificaByIdSeMatriculeiAlgumCurso/{idAluno}/{uuidCurso}")
    @Cacheable(value = "listaDeMatriculas")
    public ResponseEntity<? extends Boolean> verificaByIdSeMatriculeiAlgumCurso(@PathVariable Long idAluno, @PathVariable String uuidCurso){

        try{
            Optional<Matricula> byAlunoIdAndCurso_uuid = matriculaRepository.findByAlunoIdAndCurso_Uuid(idAluno, uuidCurso);
            if(byAlunoIdAndCurso_uuid.isPresent()){
                return ResponseEntity.ok(Boolean.TRUE);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Boolean.FALSE);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeMatriculas", allEntries = true)
    public ResponseEntity<? extends MatriculasDto> saveMatricula(@RequestBody MatriculaForm matriculaForm, UriComponentsBuilder uriBuilder){
        Optional<Matricula> byAlunoIdAndCursoId = matriculaRepository.findByAlunoIdAndCursoId(matriculaForm.getIdAluno(), matriculaForm.getIdCurso());

        if (!byAlunoIdAndCursoId.isPresent()){
            Matricula matricula = matriculaForm.save(matriculaRepository,alunoRepository,cursoRepository);
            URI uri = uriBuilder.path("/v1/matricula/{id}").buildAndExpand(matricula.getId()).toUri();
            return ResponseEntity.created(uri).body(new MatriculasDto(matricula));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/verificaById/{idAluno}/{idCurso}")
    public ResponseEntity<? extends MatriculasDto> verificarSeEstouMatriculadoEmAlgumCursoPorId(@PathVariable Long idAluno, @PathVariable Long idCurso){
        try {
            MatriculasDto matriculasDtos = MatriculasDto.converter(matriculaRepository.findByAlunoIdAndCursoId(idAluno,idCurso).get());
            return ResponseEntity.ok(matriculasDtos);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/porcentagemCurso/{idAluno}/{uuidCurso}")
    public Integer verificarPorcentagemCurso(@PathVariable Long idAluno, @PathVariable String uuidCurso){
        try{
            Integer sum=0, total=0;
            List<AssistirAula> byIdAlunoAndUuidCurso = assistirAulaRepository.findByIdAlunoAndUuidCurso(idAluno, uuidCurso);

            for (Aula aula : aulaRepository.findAll()) {
                if(aula.getModulo().getCurso().getUuid().equals(uuidCurso)){
                    sum++;
                }
            }

            total = (byIdAlunoAndUuidCurso.size() * 100) / sum;

            if (total>100){
                total = 100;
            }

            return total;
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    @GetMapping("/verificaByUuid/{idAluno}/{uuid}")
    public ResponseEntity<? extends MatriculasDto> verificarSeEstouMatriculadoEmAlgumCursoPorUuid(@PathVariable Long idAluno, @PathVariable String uuid){
        try {
            MatriculasDto matriculasDtos = MatriculasDto.converter(matriculaRepository.findByAlunoIdAndCurso_Uuid(idAluno,uuid).get());
            return ResponseEntity.ok(matriculasDtos);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/verificaById/cursosConcluidos/{idAluno}")
    public ResponseEntity<? extends List<CursoDto>> verificarSeEstouMatriculadoEmAlgumCursoPorIdConcluidos(@PathVariable Long idAluno){
        List<MatriculasDto> matriculasDtos = MatriculasDto.converter(matriculaRepository.findAllByAlunoIdAndCursoConcluidoIsTrue(idAluno));
        List<CursoDto> cursoDtoList = new ArrayList<>();

        for(MatriculasDto matriculasDto:matriculasDtos){
            CursoDto cursoDto = matriculasDto.getCursoDto();
            cursoDtoList.add(cursoDto);
        }

        if (matriculasDtos.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(cursoDtoList);
    }

    @GetMapping("/verificaById/cursoConcluido/{idAluno}/{uuid}")
    public ResponseEntity<? extends Boolean> verificaSeConcluiCurso(@PathVariable Long idAluno, @PathVariable String uuid){
        Matricula byAlunoIdAndCurso_uuid = matriculaRepository.findByAlunoIdAndCurso_Uuid(idAluno, uuid).get();
        try{
            return ResponseEntity.ok(byAlunoIdAndCurso_uuid.getCursoConcluido());
        }catch (Exception e){
            return ResponseEntity.ok(Boolean.FALSE);
        }
    }

    @GetMapping("/verificaById/cursosPausados/{idAluno}")
    public ResponseEntity<? extends List<CursoDto>> verificarSePauseiAlgumCursoNaMinhaMatricula(@PathVariable Long idAluno){
        List<MatriculasDto> matriculasDtos = MatriculasDto.converter(matriculaRepository.findAllByAlunoIdAndCursoPausadoIsTrue(idAluno));
        List<CursoDto> cursoDtoList = new ArrayList<>();

        for(MatriculasDto matriculasDto:matriculasDtos){
            CursoDto cursoDto = matriculasDto.getCursoDto();
            cursoDtoList.add(cursoDto);
        }

        if (matriculasDtos.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(cursoDtoList);
    }

    @GetMapping("/assistirAula/{idAluno}/{idAula}")
    public Boolean assistirAula(@PathVariable Long idAluno, @PathVariable Long idAula){

        try{
            AssistirAula assistirAula = assistirAulaRepository.findByIdAlunoAndIdAula(idAluno,idAula);

            if (assistirAula.getAssistido()){
                return Boolean.TRUE;
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return Boolean.FALSE;
    }


    @GetMapping("/quantidadeDePessoasMatriculasEmUmCurso/{uuid}")
    public ResponseEntity<? extends Integer> findAllAlunosMatriculadosEmUmCurso(@PathVariable String uuid){
        Integer total = matriculaRepository.findByAllMatriculasSum(uuid);
        if (total.equals(null))
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(total);
    }

    @PostMapping("/assistirAulaSave/{idAluno}/{uuidCurso}/{idAula}")
    public ResponseEntity<AssistirAula> assistirAulaSave(@PathVariable Long idAluno, @PathVariable String uuidCurso, @PathVariable Long idAula, UriComponentsBuilder uriBuilder){
        Optional<AssistirAula> byIdAlunoAndUuidCursoAndIdAula = assistirAulaRepository.findByIdAlunoAndUuidCursoAndIdAula(idAluno, uuidCurso, idAula);

        if (byIdAlunoAndUuidCursoAndIdAula.isPresent()){
            return ResponseEntity.notFound().build();
        }
        else{
            AssistirAula assistirAula = new AssistirAula(idAluno,uuidCurso,idAula);
            AssistirAula save = assistirAulaRepository.save(assistirAula);
            URI uri = uriBuilder.path("/v1/matricula/{id}").buildAndExpand(save.getId()).toUri();
            return ResponseEntity.created(uri).body(save);
        }
    }

    @PutMapping("/concluirCursoById/{idAluno}/{idCurso}")
    public ResponseEntity<MatriculasDto> concluirCursoById(@PathVariable Long idAluno, @PathVariable Long idCurso, UriComponentsBuilder uriBuilder){
        Matricula matricula = matriculaRepository.findByAlunoIdAndCursoId(idAluno,idCurso).get();
        matricula.setCursoConcluido(Boolean.TRUE);
        matriculaRepository.save(matricula);
        URI uri = uriBuilder.path("/v1/matricula/{id}").buildAndExpand(matricula.getId()).toUri();
        return ResponseEntity.created(uri).body(MatriculasDto.converter(matricula));
    }

    @PutMapping("/concluirCursoByUuid/{idAluno}/{uuid}")
    public ResponseEntity<MatriculasDto> concluirCursoByUuid(@PathVariable Long idAluno, @PathVariable String uuid, UriComponentsBuilder uriBuilder){
        Matricula matricula = matriculaRepository.findByAlunoIdAndCurso_Uuid(idAluno,uuid).get();
        matricula.setCursoConcluido(Boolean.TRUE);
        matriculaRepository.save(matricula);
        URI uri = uriBuilder.path("/v1/matricula/{id}").buildAndExpand(matricula.getId()).toUri();
        return ResponseEntity.created(uri).body(MatriculasDto.converter(matricula));
    }

    @DeleteMapping("/todo")
    public Boolean a(){
        assistirAulaRepository.deleteAll();
        return Boolean.TRUE;
    }

    @DeleteMapping("/todos")
    public Boolean b(){
        matriculaRepository.deleteAll();
        return Boolean.TRUE;
    }
}
