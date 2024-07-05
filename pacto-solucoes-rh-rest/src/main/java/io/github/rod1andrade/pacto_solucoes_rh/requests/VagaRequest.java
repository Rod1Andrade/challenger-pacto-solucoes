package io.github.rod1andrade.pacto_solucoes_rh.requests;

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
public class VagaRequest {
    @NotNull
    @Size(max = 255, message = "Tamanho maximo do titulo deve ser de 255")
    private String titulo;

    @NotNull
    @Size(max = 255, message = "Tamanho maximo do subtitulo deve ser de 255")
    private String subtitulo;

    @NotNull
    @Size(max = 255, message = "Tamanho maximo da descricao deve ser de 255")
    private String descricao;

    @NotNull(message = "Texto nao pode ser nulo, deve conter as informações mais relevantes da vaga.")
    private String texto;
}
