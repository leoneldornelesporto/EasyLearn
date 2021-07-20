package br.com.easylearn.repository;

import br.com.easylearn.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno,Long> {
}
