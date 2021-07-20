package br.com.easylearn.repository;

import br.com.easylearn.domain.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia,Long> {
}
