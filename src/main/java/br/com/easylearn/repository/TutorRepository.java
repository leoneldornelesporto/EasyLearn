package br.com.easylearn.repository;

import br.com.easylearn.domain.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor,Long> {
}
