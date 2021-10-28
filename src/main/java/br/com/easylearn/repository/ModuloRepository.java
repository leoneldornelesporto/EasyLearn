package br.com.easylearn.repository;

import br.com.easylearn.domain.Matricula;
import br.com.easylearn.domain.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuloRepository extends JpaRepository<Modulo,Long> {
    Modulo findByCursoUuid(String uuid);
}
