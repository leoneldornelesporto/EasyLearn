package br.com.easylearn.repository;

import br.com.easylearn.domain.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AulaRepository extends JpaRepository<Aula,Long> {
}
