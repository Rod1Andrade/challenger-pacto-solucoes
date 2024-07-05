package io.github.rod1andrade.pacto_solucoes_rh.repositories;

import io.github.rod1andrade.pacto_solucoes_rh.entities.Vaga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    @Query("SELECT v FROM Vaga v WHERE LOWER(v.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    Page<Vaga> findByTituloContainingIgnoreCase(@Param("titulo") String titulo, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END " +
            "FROM Vaga v " +
            "JOIN v.usuarios u " +
            "WHERE v.id = :idVaga AND u.id = :idUsuario")
    boolean isUsuarioInscrito(@Param("idVaga") Long idVaga, @Param("idUsuario") Long idUsuario);
}
