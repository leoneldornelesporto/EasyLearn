package br.com.easylearn.repository;

import br.com.easylearn.domain.AssistirAula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssistirAulaRepository extends JpaRepository<AssistirAula,Long> {
    Optional<AssistirAula> findByIdAula(Long idAula);
    AssistirAula findByIdAlunoAndIdAula(Long idAluno, Long idAula);
    List<AssistirAula> findByIdAlunoAndUuidCurso(Long idAluno, String uuidCurso);
}
