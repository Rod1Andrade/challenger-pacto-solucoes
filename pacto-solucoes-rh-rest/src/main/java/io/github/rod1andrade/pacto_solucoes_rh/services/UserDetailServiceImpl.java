package io.github.rod1andrade.pacto_solucoes_rh.services;

import io.github.rod1andrade.pacto_solucoes_rh.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByNome(username);
        if(Objects.isNull(usuario)) throw new UsernameNotFoundException("Usuário não encontrado");

        return new User(
            usuario.getNome(),
            usuario.getSenha(),
            usuario.getPapeis()
                    .stream()
                    .map(papel -> new SimpleGrantedAuthority(papel.getNome()))
                    .toList()
        );
    }
}
