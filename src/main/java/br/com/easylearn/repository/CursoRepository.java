package br.com.easylearn.repository;

import br.com.easylearn.domain.Curso;
import br.com.easylearn.domain.Formacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	List<Curso> findByNomeContaining(String nome);
	Curso findByUuid(String uuid);
	List<Curso> findByCategoriaId(Long idCategoria);
	List<Curso> findCursosByAtivoTrue();
	@Query("SELECT c.formacao FROM Curso c WHERE c.uuid = :uuid")
	List<Formacao> findAllFormacaoByCursoUuid(String uuid);
	List<Formacao> findAllByFormacaoId(Long id);

	Curso findByNome(String nomeCurso);
}
