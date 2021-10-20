package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.CursoDto;
import br.com.easylearn.controller.dto.MatriculasDto;
import br.com.easylearn.controller.form.MatriculaForm;
import br.com.easylearn.domain.AssistirAula;
import br.com.easylearn.domain.Matricula;
import br.com.easylearn.domain.Modulo;
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

    @Autowired
    public MatriculaController(MatriculaRepository matriculaRepository, AlunoRepository alunoRepository, CursoRepository cursoRepository, AssistirAulaRepository assistirAulaRepository, ModuloRepository moduloRepository) {
        this.matriculaRepository = matriculaRepository;
        this.moduloRepository = moduloRepository;
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
        this.assistirAulaRepository = assistirAulaRepository;
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

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeMatriculas", allEntries = true)
    public ResponseEntity<? extends MatriculasDto> saveMatricula(@RequestBody MatriculaForm matriculaForm, UriComponentsBuilder uriBuilder){
        Matricula matricula = matriculaForm.save(matriculaRepository,alunoRepository,cursoRepository);
        URI uri = uriBuilder.path("/v1/matricula/{id}").buildAndExpand(matricula.getId()).toUri();
        return ResponseEntity.created(uri).body(new MatriculasDto(matricula));
    }

    @GetMapping("/verificaById/{idAluno}/{idCurso}")
    public ResponseEntity<? extends MatriculasDto> verificarSeEstouMatriculadoEmAlgumCursoPorId(@PathVariable Long idAluno, @PathVariable Long idCurso){
        try {
            MatriculasDto matriculasDtos = MatriculasDto.converter(matriculaRepository.findByAlunoIdAndCursoId(idAluno,idCurso));
            return ResponseEntity.ok(matriculasDtos);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/verificaByUuid/{idAluno}/{uuid}")
    public ResponseEntity<? extends MatriculasDto> verificarSeEstouMatriculadoEmAlgumCursoPorUuid(@PathVariable Long idAluno, @PathVariable String uuid){
        try {
            MatriculasDto matriculasDtos = MatriculasDto.converter(matriculaRepository.findByAlunoIdAndCurso_Uuid(idAluno,uuid));
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
        Matricula byAlunoIdAndCurso_uuid = matriculaRepository.findByAlunoIdAndCurso_Uuid(idAluno, uuid);

        if (byAlunoIdAndCurso_uuid.equals(null))
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(byAlunoIdAndCurso_uuid.getCursoConcluido());
    }

    @GetMapping("/verificaById/porcentagem/aluno/{idAluno}/curso/{uuid}")
    public ResponseEntity<Integer> verificaPorcentagemDoCurso(@PathVariable Long idAluno, @PathVariable String uuid){
        Matricula byAlunoIdAndCurso_uuid = matriculaRepository.findByAlunoIdAndCurso_Uuid(idAluno, uuid);
        List<Modulo> byCursoUuid = moduloRepository.findByCursoUuid(uuid);

        if (byAlunoIdAndCurso_uuid.equals(null))
            return ResponseEntity.notFound().build();
        else {
            List<AssistirAula> byIdAlunoAndUuidCurso = assistirAulaRepository.findByIdAlunoAndUuidCurso(idAluno, uuid);
            Integer total = verificaQuantidadeTotalDeAulas(byCursoUuid);
            Integer porcentagem = (byIdAlunoAndUuidCurso.size() * 100) / total;
            byAlunoIdAndCurso_uuid.setProgresso(porcentagem);
            Matricula save = matriculaRepository.save(byAlunoIdAndCurso_uuid);
            return ResponseEntity.ok(save.getProgresso());
        }
    }

    private Integer verificaQuantidadeTotalDeAulas(List<Modulo> moduloList) {
        Integer total=0;
        for (Modulo modulo : moduloList){
            total+=modulo.getAulaList().size();
        }
        return total;
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
        Matricula matricula = matriculaRepository.findByAlunoIdAndCursoId(idAluno,idCurso);
        matricula.setCursoConcluido(Boolean.TRUE);
        matriculaRepository.save(matricula);
        URI uri = uriBuilder.path("/v1/matricula/{id}").buildAndExpand(matricula.getId()).toUri();
        return ResponseEntity.created(uri).body(MatriculasDto.converter(matricula));
    }

    @PutMapping("/concluirCursoByUuid/{idAluno}/{uuid}")
    public ResponseEntity<MatriculasDto> concluirCursoByUuid(@PathVariable Long idAluno, @PathVariable String uuid, UriComponentsBuilder uriBuilder){
        Matricula matricula = matriculaRepository.findByAlunoIdAndCurso_Uuid(idAluno,uuid);
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
}
