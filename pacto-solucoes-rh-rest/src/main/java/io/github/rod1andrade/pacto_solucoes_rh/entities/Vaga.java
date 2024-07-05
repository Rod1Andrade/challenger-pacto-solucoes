package io.github.rod1andrade.pacto_solucoes_rh.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_VAGAS")
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;

    @Column(name = "subtitulo", nullable = false, length = 255)
    private String subtitulo;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    @Column(name = "texto", nullable = false)
    private String texto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "criado_por", nullable = false)
    private Usuario criadoPor;

    @ManyToMany
    @JoinTable(
            name = "TB_VAGAS_APLICADA_USUARIO",
            joinColumns = @JoinColumn(name = "id_vaga"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private Set<Usuario> usuarios = new HashSet<>();
}
