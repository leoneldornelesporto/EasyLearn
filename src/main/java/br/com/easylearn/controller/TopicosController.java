package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.DetalhesDoTopicoDto;
import br.com.easylearn.controller.dto.TopicoDto;
import br.com.easylearn.controller.form.AtualizacaoTopicoForm;
import br.com.easylearn.controller.form.TopicoForm;
import br.com.easylearn.domain.Topico;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    private final TopicosRepository topicosRepository;
    private final CursoRepository cursoRepository;

    @Autowired
    public TopicosController(TopicosRepository topicosRepository, CursoRepository cursoRepository) {
        this.topicosRepository = topicosRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public ResponseEntity<? extends List<TopicoDto>> findAllTopicos(){
        List<TopicoDto> topicoDtos = TopicoDto.converter(topicosRepository.findAll());

        if (!topicoDtos.isEmpty()){
            return ResponseEntity.ok(topicoDtos);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicosRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
        Optional<Topico> topico = topicosRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody AtualizacaoTopicoForm form) {
        Optional<Topico> optional = topicosRepository.findById(id);
        if (optional.isPresent()) {
            Topico topico = form.atualizar(id, topicosRepository);
            return ResponseEntity.ok(new TopicoDto(topico));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Topico> optional = topicosRepository.findById(id);
        if (optional.isPresent()) {
            topicosRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
