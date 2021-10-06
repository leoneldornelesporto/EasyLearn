package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.FormacaoDto;
import br.com.easylearn.controller.dto.FormacoesDto;
import br.com.easylearn.controller.form.AtualizacaoFormacaoForm;
import br.com.easylearn.controller.form.FormacaoForm;
import br.com.easylearn.domain.Categoria;
import br.com.easylearn.domain.Formacao;
import br.com.easylearn.repository.CategoriaRepository;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.FormacaoRepository;
import br.com.easylearn.repository.ModuloRepository;
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

@RequestMapping("v1/protectedA/formacao")
//@PreAuthorize("hasRole('PROFESSOR')")
@RestController
public class FormacaoController {

    private final FormacaoRepository formacaoRepository;
    private final CursoRepository cursoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ModuloRepository moduloRepository;

    @Autowired
    public FormacaoController(FormacaoRepository formacaoRepository, CursoRepository cursoRepository, CategoriaRepository categoriaRepository, ModuloRepository moduloRepository) {
        this.formacaoRepository = formacaoRepository;
        this.cursoRepository = cursoRepository;
        this.categoriaRepository = categoriaRepository;
        this.moduloRepository = moduloRepository;
    }

    @GetMapping
    public ResponseEntity<? extends List<FormacaoDto>> findAllFormacoes(){
        List<FormacaoDto> formacaoDtoList = FormacaoDto.converter(formacaoRepository.findAll(),moduloRepository);
        if (formacaoDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(formacaoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends List<FormacaoDto>> findAllFormacoes(@PathVariable Long id){
        List<FormacaoDto> formacaoDtoList = FormacaoDto.converter(formacaoRepository.findAllById(id),moduloRepository);
        if (formacaoDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(formacaoDtoList);
    }


    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<? extends List<FormacoesDto>> findAllFormacoesByIdCategoria(@PathVariable Long idCategoria){
        List<FormacoesDto> formacaoDtoList = FormacoesDto.converter(formacaoRepository.findAllByCategoriaId(idCategoria));
        if (formacaoDtoList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(formacaoDtoList);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<? extends FormacaoDto> saveFormacao(@RequestBody FormacaoForm formacaoForm, UriComponentsBuilder uriBuilder){
        Formacao formacao  = formacaoForm.save(formacaoRepository,cursoRepository,categoriaRepository);
        URI uri = uriBuilder.path("/v1/formacao/{id}").buildAndExpand(formacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new FormacaoDto(formacao));
    }

    @PutMapping("{idFormacao}")
    @Transactional
    public ResponseEntity<? extends FormacaoDto> atualizarFormacao(@PathVariable Long idFormacao, @RequestBody AtualizacaoFormacaoForm form) {
        Optional<Formacao> optional = formacaoRepository.findById(idFormacao);
        if (optional.isPresent()) {
            Formacao formacao = form.atualizar(idFormacao, formacaoRepository,cursoRepository,categoriaRepository);
            return ResponseEntity.ok(new FormacaoDto(formacao));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{idFormacao}")
    @Transactional
    public ResponseEntity<?> removerFormacao(@PathVariable Long idFormacao) {
        Optional<Formacao> optional = formacaoRepository.findById(idFormacao);
        if (optional.isPresent()) {
            formacaoRepository.deleteById(idFormacao);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
