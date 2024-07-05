package io.github.rod1andrade.pacto_solucoes_rh.services;

import io.github.rod1andrade.pacto_solucoes_rh.entities.Usuario;
import io.github.rod1andrade.pacto_solucoes_rh.exceptions.PactoSolucoesRHException;
import io.github.rod1andrade.pacto_solucoes_rh.repositories.UsuarioRepository;
import io.github.rod1andrade.pacto_solucoes_rh.requests.UsuarioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public void registrar(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.getNome());
        usuario.setSenha(passwordEncoder.encode(usuarioRequest.getSenha()));
        usuario.setAtivo(Boolean.TRUE);

        try {
            usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new PactoSolucoesRHException("Não foi possível registrar novo usuário", 1);
        }
    }

    public Usuario obterByNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }
}
