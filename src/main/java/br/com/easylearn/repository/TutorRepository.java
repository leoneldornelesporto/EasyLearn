package br.com.easylearn.repository;

import br.com.easylearn.domain.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor,Long> {
    Optional<Tutor> findByUuid(String uuid);
}
