package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.CursoDto;
import br.com.easylearn.controller.dto.MatriculasDto;
import br.com.easylearn.controller.form.MatriculaForm;
import br.com.easylearn.domain.AssistirAula;
import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Matricula;
import br.com.easylearn.domain.Modulo;
import br.com.easylearn.repository.AlunoRepository;
import br.com.easylearn.repository.AssistirAulaRepository;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.MatriculaRepository;
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

@RestController
@RequestMapping("v1/protectedA/matricula")
@PreAuthorize("hasRole('ALUNO')")
public class MatriculaController {

    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;
    private final AssistirAulaRepository assistirAulaRepository;

    @Autowired
    public MatriculaController(MatriculaRepository matriculaRepository, AlunoRepository alunoRepository, CursoRepository cursoRepository, AssistirAulaRepository assistirAulaRepository) {
        this.matriculaRepository = matriculaRepository;
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

    @GetMapping("/verificaById/porcentagem/aluno/{idAluno}/curso/{uuid}")
    public ResponseEntity<List<Modulo>> verificaPorcentagemDoCurso(@PathVariable Long idAluno, @PathVariable String uuid){
        Matricula byAlunoIdAndCurso_uuid = matriculaRepository.findByAlunoIdAndCurso_Uuid(idAluno, uuid);
        Curso curso = cursoRepository.findByUuid(uuid);

        if (byAlunoIdAndCurso_uuid.equals(null))
            return ResponseEntity.notFound().build();
        else {
            List<AssistirAula> byIdAlunoAndUuidCurso = assistirAulaRepository.findByIdAlunoAndUuidCurso(idAluno, uuid);
            //Integer total = verificaQuantidadeTotalDeAulas(byAlunoIdAndCurso_uuid.getCurso().getModuloList());
            //Integer porcentagem = (byIdAlunoAndUuidCurso.size() * 100) / total;
            //byAlunoIdAndCurso_uuid.setProgresso(porcentagem);
            //Matricula save = matriculaRepository.save(byAlunoIdAndCurso_uuid);
            return ResponseEntity.ok(curso.getModuloList());
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
        AssistirAula assistirAula = new AssistirAula(idAluno,uuidCurso,idAula);
        AssistirAula save = assistirAulaRepository.save(assistirAula);
        URI uri = uriBuilder.path("/v1/matricula/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(uri).body(save);
    }

    @PutMapping("/concluirCurso/{idAluno}/{idCurso}")
    public ResponseEntity<MatriculasDto> concluirCurso(@PathVariable Long idAluno, @PathVariable Long idCurso, UriComponentsBuilder uriBuilder){
        Matricula matricula = matriculaRepository.findByAlunoIdAndCursoId(idAluno,idCurso);
        matricula.setCursoConcluido(Boolean.TRUE);
        matriculaRepository.save(matricula);
        URI uri = uriBuilder.path("/v1/matricula/{id}").buildAndExpand(matricula.getId()).toUri();
        return ResponseEntity.created(uri).body(MatriculasDto.converter(matricula));
    }
}
