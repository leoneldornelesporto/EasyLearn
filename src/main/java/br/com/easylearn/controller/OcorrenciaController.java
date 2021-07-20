package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.OcorrenciaDto;
import br.com.easylearn.controller.form.OcorrenciaForm;
import br.com.easylearn.domain.Ocorrencia;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.OcorrenciaRepository;
import br.com.easylearn.repository.UsuarioRepository;
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

@RestController
@RequestMapping("v1/ocorrencia")
public class OcorrenciaController {

    private final OcorrenciaRepository ocorrenciaRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public OcorrenciaController(OcorrenciaRepository ocorrenciaRepository, CursoRepository cursoRepository, UsuarioRepository usuarioRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ALUNO') or hasRole('PROFESSOR') or hasRole('TUTOR')")
    @Cacheable(value = "listaDeOcorrencias")
    public ResponseEntity<? extends List<OcorrenciaDto>> findAllOcorrencias(){
        List<OcorrenciaDto> ocorrenciaDtos = OcorrenciaDto.converter(ocorrenciaRepository.findAll());
        if (ocorrenciaDtos.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(ocorrenciaDtos);
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ALUNO') or hasRole('PROFESSOR') or hasRole('TUTOR')")
    @CacheEvict(value = "listaDeOcorrencias", allEntries = true)
    public ResponseEntity<? extends OcorrenciaDto> saveOcorrencia(@RequestBody OcorrenciaForm ocorrenciaForm, UriComponentsBuilder uriBuilder){
        Ocorrencia ocorrencia = ocorrenciaForm.save(ocorrenciaRepository,usuarioRepository,cursoRepository);
        URI uri = uriBuilder.path("/v1/ocorrencia/{id}").buildAndExpand(ocorrencia.getId()).toUri();
        return ResponseEntity.created(uri).body(new OcorrenciaDto(ocorrencia));
    }
}
