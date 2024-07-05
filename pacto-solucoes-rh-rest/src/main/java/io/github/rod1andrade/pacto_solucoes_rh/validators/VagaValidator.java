package io.github.rod1andrade.pacto_solucoes_rh.validators;

import io.github.rod1andrade.pacto_solucoes_rh.entities.Usuario;
import io.github.rod1andrade.pacto_solucoes_rh.enums.PapeisEnum;
import io.github.rod1andrade.pacto_solucoes_rh.exceptions.PactoSolucoesRHException;
import org.springframework.stereotype.Component;

@Component
public class VagaValidator {
    public void validarAnuncioVaga(Usuario usuario) {
        usuario.getPapeis()
                .stream()
                .filter(papel -> papel.getNome().equals(PapeisEnum.ADMIN.name()))
                .findFirst()
                .orElseThrow(() -> new PactoSolucoesRHException("Necessario admin para anunciar vaga", 1892));
    }
}
