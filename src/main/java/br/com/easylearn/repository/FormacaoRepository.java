package br.com.easylearn.repository;

import br.com.easylearn.domain.Formacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormacaoRepository extends JpaRepository<Formacao,Long> {
}
