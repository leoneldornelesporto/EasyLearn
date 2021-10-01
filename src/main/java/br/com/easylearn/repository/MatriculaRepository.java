package br.com.easylearn.repository;

import br.com.easylearn.domain.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula,Long> {
    Matricula findByAlunoIdAndCursoId(Long idAluno, Long idCurso);
    Matricula findByAlunoIdAndCurso_Uuid(Long idAluno, String uuid);
    List<Matricula> findAllByAlunoIdAndCursoConcluidoIsTrue(Long id);
    List<Matricula> findAllByAlunoIdAndCursoPausadoIsTrue(Long idAluno);
}
