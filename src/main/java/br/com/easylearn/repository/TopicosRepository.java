package br.com.easylearn.repository;

import br.com.easylearn.domain.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicosRepository extends JpaRepository<Topico, Long> {
}
