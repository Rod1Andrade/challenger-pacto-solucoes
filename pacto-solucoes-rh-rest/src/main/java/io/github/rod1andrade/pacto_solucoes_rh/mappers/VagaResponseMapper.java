package io.github.rod1andrade.pacto_solucoes_rh.mappers;

import io.github.rod1andrade.pacto_solucoes_rh.entities.Vaga;
import io.github.rod1andrade.pacto_solucoes_rh.responses.VagaResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VagaResponseMapper extends BaseResponseMapper<Vaga, VagaResponse> {
}
