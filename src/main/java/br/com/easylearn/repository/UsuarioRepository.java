package br.com.easylearn.repository;

import br.com.easylearn.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);
	Optional<Usuario> findByEmailAndSenha(String email, String senha);
	Usuario findByNomeCompleto(String nomeUsuario);
	Page<Usuario> findByNomeCompleto(String nomeUsuario, Pageable paginacao);

}
