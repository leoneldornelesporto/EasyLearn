package br.com.easylearn.repository;

import br.com.easylearn.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByUuidCursoAndIdAlunoAndStatusContaining(String uuidCurso, Long idAluno, String status);
}
