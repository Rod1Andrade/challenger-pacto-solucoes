package io.github.rod1andrade.pacto_solucoes_rh.responses;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VagaResponse {
    private Long id;
    private String titulo;
    private String subtitulo;
    private String descricao;
    private String texto;
    private boolean permissaoEditar;
}
