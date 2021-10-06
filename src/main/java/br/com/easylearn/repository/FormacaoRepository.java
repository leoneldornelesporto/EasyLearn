package br.com.easylearn.repository;

import br.com.easylearn.domain.Formacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormacaoRepository extends JpaRepository<Formacao,Long> {
    List<Formacao> findAllByCategoriaId(Long idCategoria);
    List<Formacao> findAllById(Long idCategoria);
}
