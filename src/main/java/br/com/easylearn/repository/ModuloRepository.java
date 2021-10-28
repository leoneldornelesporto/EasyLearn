package br.com.easylearn.repository;

import br.com.easylearn.domain.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuloRepository extends JpaRepository<Modulo,Long> {
    Modulo findByCursoUuid(String uuid);
    List<Modulo> findAllByCursoId(Long id);
}
