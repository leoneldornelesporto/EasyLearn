package br.com.easylearn.config.security;

import br.com.easylearn.domain.Usuario;
import br.com.easylearn.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public AutenticacaoService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Usuario user = Optional.ofNullable(usuarioRepository.findByNomeCompleto(userName)).orElseThrow(() -> new UsernameNotFoundException("User not found"));

		List<GrantedAuthority> authorityListTutor = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_TUTOR");
		List<GrantedAuthority> authorityListProfessor = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_PROFESSOR");
		List<GrantedAuthority> authorityListAluno = AuthorityUtils.createAuthorityList("ROLE_USER","ROLE_ALUNO");

		if(user.getTutor() && user.getAtivo()){
			return new User(user.getUsername(),user.getPassword(), authorityListTutor);
		}
		else {
			if(user.getProfessor() && user.getAtivo()){
				return new User(user.getUsername(),user.getPassword(), authorityListProfessor);
			}
			if(user.getAluno() && user.getAtivo()){
				return new User(user.getUsername(),user.getPassword(), authorityListAluno);
			}
		}
		return null;
	}
}
