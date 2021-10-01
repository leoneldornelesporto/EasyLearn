package br.com.easylearn.repository;

import br.com.easylearn.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno,Long> {
    Optional<Aluno> findByUuid(String uuid);
}
