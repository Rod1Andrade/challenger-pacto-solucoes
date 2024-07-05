package io.github.rod1andrade.pacto_solucoes_rh.repositories;

import io.github.rod1andrade.pacto_solucoes_rh.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNome(String username);
}
