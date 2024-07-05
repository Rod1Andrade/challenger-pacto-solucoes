package io.github.rod1andrade.pacto_solucoes_rh.repositories;

import io.github.rod1andrade.pacto_solucoes_rh.entities.Papel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {
}
