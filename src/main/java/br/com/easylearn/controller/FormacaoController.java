package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.FormacaoDto;
import br.com.easylearn.controller.form.AtualizacaoFormacaoForm;
import br.com.easylearn.controller.form.FormacaoForm;
import br.com.easylearn.domain.Formacao;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.FormacaoRepository;
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
import java.util.Optional;

@RequestMapping("v1/formacao")
@PreAuthorize("hasRole('PROFESSOR')")
@RestController
public class FormacaoController {

    private final FormacaoRepository formacaoRepository;
    private final CursoRepository cursoRepository;

    @Autowired
    public FormacaoController(FormacaoRepository formacaoRepository, CursoRepository cursoRepository) {
        this.formacaoRepository = formacaoRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    @Cacheable(value = "listaDeFormacao")
    public ResponseEntity<? extends List<FormacaoDto>> findAllFormacoes(){
        List<FormacaoDto> formacaoDtoList = FormacaoDto.converter(formacaoRepository.findAll());
        if (formacaoDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(formacaoDtoList);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeFormacao", allEntries = true)
    public ResponseEntity<? extends FormacaoDto> saveFormacao(@RequestBody FormacaoForm formacaoForm, UriComponentsBuilder uriBuilder){
        Formacao formacao  = formacaoForm.save(formacaoRepository,cursoRepository);
        URI uri = uriBuilder.path("/v1/formacao/{id}").buildAndExpand(formacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new FormacaoDto(formacao));
    }

    @PutMapping("{idFormacao}")
    @Transactional
    @CacheEvict(value = "listaDeFormacao", allEntries = true)
    public ResponseEntity<? extends FormacaoDto> atualizarFormacao(@PathVariable Long idFormacao, @RequestBody AtualizacaoFormacaoForm form) {
        Optional<Formacao> optional = formacaoRepository.findById(idFormacao);
        if (optional.isPresent()) {
            Formacao formacao = form.atualizar(idFormacao, formacaoRepository, cursoRepository);
            return ResponseEntity.ok(new FormacaoDto(formacao));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{idFormacao}")
    @Transactional
    @CacheEvict(value = "listaDeFormacao", allEntries = true)
    public ResponseEntity<?> removerFormacao(@PathVariable Long idFormacao) {
        Optional<Formacao> optional = formacaoRepository.findById(idFormacao);
        if (optional.isPresent()) {
            formacaoRepository.deleteById(idFormacao);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
