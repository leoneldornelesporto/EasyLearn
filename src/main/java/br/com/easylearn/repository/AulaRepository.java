package br.com.easylearn.repository;

import br.com.easylearn.domain.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AulaRepository extends JpaRepository<Aula,Long> {
    List<Aula> findByModuloId(Long id);
    Optional<Aula> findByIdAndModuloCursoUuid(Long idAula, String uuid);
}
