package br.com.easylearn.repository;

import br.com.easylearn.domain.AssistirAula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssistirAulaRepository extends JpaRepository<AssistirAula,Long> {
    AssistirAula findByIdAlunoAndIdAula(Long idAluno, Long idAula);
    List<AssistirAula> findByIdAlunoAndUuidCurso(Long idAluno, String uuidCurso);
}
