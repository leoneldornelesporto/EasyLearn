package br.com.easylearn.controller;

import br.com.easylearn.controller.dto.DetalhesDoTopicoDto;
import br.com.easylearn.controller.dto.RespostaDto;
import br.com.easylearn.controller.dto.TopicoDto;
import br.com.easylearn.controller.form.AtualizacaoTopicoForm;
import br.com.easylearn.controller.form.RespostaForm;
import br.com.easylearn.controller.form.TopicoForm;
import br.com.easylearn.domain.Resposta;
import br.com.easylearn.domain.StatusTopico;
import br.com.easylearn.domain.Topico;
import br.com.easylearn.repository.CursoRepository;
import br.com.easylearn.repository.RespostaRepository;
import br.com.easylearn.repository.TopicosRepository;
import br.com.easylearn.repository.UsuarioRepository;
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
    private final UsuarioRepository usuarioRepository;
    private final RespostaRepository respostaRepository;

    @Autowired
    public TopicosController(TopicosRepository topicosRepository, CursoRepository cursoRepository, UsuarioRepository usuarioRepository, RespostaRepository respostaRepository) {
        this.topicosRepository = topicosRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
        this.respostaRepository = respostaRepository;
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
        Topico topico = form.converter(cursoRepository,usuarioRepository);
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

    @PutMapping("responder/{id}")
    @Transactional
    public ResponseEntity<RespostaDto> responderTopico(@PathVariable Long id, @RequestBody RespostaForm respostaForm) {
        Optional<Topico> optional = topicosRepository.findById(id);
        if (optional.isPresent()) {
            RespostaDto respostaDto = respostaForm.save(id,usuarioRepository,topicosRepository,respostaRepository);
            return ResponseEntity.ok(respostaDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("solucionar/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> solucionarTopico(@PathVariable Long id) {
        Optional<Topico> optional = topicosRepository.findById(id);
        if (optional.isPresent()) {
            optional.get().setStatus(StatusTopico.SOLUCIONADO);
            return ResponseEntity.ok(new TopicoDto(optional.get()));
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
