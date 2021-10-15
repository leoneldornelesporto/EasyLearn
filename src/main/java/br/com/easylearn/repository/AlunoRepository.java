package br.com.easylearn.repository;

import br.com.easylearn.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno,Long> {
    Optional<Aluno> findByUuid(String uuid);

    Optional<Aluno> findByIdAndEmail(Long id, String email);

    Optional<Aluno> findByEmail(String email);

    Optional<Aluno> findByCpf(String cpf);
}
