package br.com.easylearn.repository;

import br.com.easylearn.domain.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula,Long> {
}
