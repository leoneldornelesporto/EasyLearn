package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.CategoriaDto;
import br.com.easylearn.controller.form.AtualizacaoCategoriaForm;
import br.com.easylearn.controller.form.CategoriaForm;
import br.com.easylearn.domain.Categoria;
import br.com.easylearn.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

@RequestMapping("v1/categoria")
@PreAuthorize("hasRole('PROFESSOR')")
@RestController
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeCategorias", allEntries = true)
    public ResponseEntity<? extends CategoriaDto> saveCategoria(@RequestBody CategoriaForm categoriaForm, UriComponentsBuilder uriBuilder){
        Categoria categoria  = categoriaForm.save(categoriaRepository);
        URI uri = uriBuilder.path("/v1/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }

    @PutMapping("{idCategoria}")
    @Transactional
    @CacheEvict(value = "listaDeCategorias", allEntries = true)
    public ResponseEntity<? extends CategoriaDto> atualizarCategoria(@PathVariable Long idCategoria, @RequestBody AtualizacaoCategoriaForm form) {
        Optional<Categoria> optional = categoriaRepository.findById(idCategoria);
        if (optional.isPresent()) {
            Categoria categoria = form.atualizar(idCategoria, categoriaRepository);
            return ResponseEntity.ok(new CategoriaDto(categoria));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{idCategoria}")
    @Transactional
    @CacheEvict(value = "listaDeCategorias", allEntries = true)
    public ResponseEntity<?> removerTutor(@PathVariable Long idCategoria) {
        Optional<Categoria> optional = categoriaRepository.findById(idCategoria);
        if (optional.isPresent()) {
            categoriaRepository.deleteById(idCategoria);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
