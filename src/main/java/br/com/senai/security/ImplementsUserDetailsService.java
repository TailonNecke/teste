package br.com.senai.security;

import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.model.Perfil;
import br.com.senai.domain.repository.PerfilRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

    private PerfilRepository perfilRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Perfil perfil = perfilRepository.findByEmail(email);

        if(perfil == null){
            throw new NegocioException("Usuário ou senha inválido.");
        }

        return new User(
                perfil.getUsername(),
                perfil.getPassword(),
                true,
                true,
                true,
                true,
                perfil.getAuthorities()
        );
    }
}
