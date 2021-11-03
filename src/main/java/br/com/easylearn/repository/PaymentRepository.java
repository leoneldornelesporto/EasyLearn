package br.com.easylearn.repository;

import br.com.easylearn.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByUuidCursoAndIdAlunoAndStatusContaining(String uuidCurso, Long idAluno, String status);
    Optional<Payment> findByUuidCursoAndIdAluno(String uuidCurso, Long idAluno);
}
