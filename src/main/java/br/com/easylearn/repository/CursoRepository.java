package br.com.easylearn.repository;

import br.com.easylearn.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);
	Curso findByUuid(String uuid);
}
