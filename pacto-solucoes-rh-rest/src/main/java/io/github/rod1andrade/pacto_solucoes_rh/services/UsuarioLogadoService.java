package io.github.rod1andrade.pacto_solucoes_rh.services;

import io.github.rod1andrade.pacto_solucoes_rh.entities.Papel;
import io.github.rod1andrade.pacto_solucoes_rh.entities.Usuario;
import io.github.rod1andrade.pacto_solucoes_rh.exceptions.PactoSolucoesRHException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioLogadoService {

    private final UsuarioService usuarioService;

    public Usuario getUsuarioLogado() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if(Objects.isNull(authentication) || Objects.isNull(authentication.getPrincipal())) {
            throw new PactoSolucoesRHException("Não contem usuário autenticado", 3);
        }

        if(! (authentication.getPrincipal() instanceof UserDetails principal)) {
            throw new PactoSolucoesRHException("Principal deve ser instancia de UserDetails", 4);
        }

        return usuarioService.obterByNome(principal.getUsername());
    }

    public Set<Papel> getPapeis(Authentication authentication) {
        if(! (authentication.getPrincipal() instanceof UserDetails principal)) {
            throw new PactoSolucoesRHException("Principal deve ser instancia de UserDetails", 4);
        }

        return usuarioService.obterByNome(principal.getUsername()).getPapeis();
    }
}
