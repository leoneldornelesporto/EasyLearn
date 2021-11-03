package br.com.easylearn.repository;

import br.com.easylearn.domain.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula,Long> {
    Optional<Matricula>  findByAlunoIdAndCursoId(Long idAluno, Long idCurso);
    Optional<Matricula>  findByAlunoIdAndCurso_Uuid(Long idAluno, String uuid);
    List<Matricula> findAllByAlunoIdAndCursoConcluidoIsTrue(Long id);
    List<Matricula> findAllByAlunoIdAndCursoPausadoIsTrue(Long idAluno);
    @Query("SELECT count(m.id) FROM Matricula m where m.curso.uuid = :uuid AND m.cursoConcluido = true")
    Integer findByAllMatriculasSum(String uuid);
}
