package br.com.easylearn.repository;

import br.com.easylearn.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	List<Curso> findByNomeContaining(String nome);
	Curso findByUuid(String uuid);
	List<Curso> findByCategoriaId(Long idCategoria);
	List<Curso> findCursosByAtivoTrue();
}
