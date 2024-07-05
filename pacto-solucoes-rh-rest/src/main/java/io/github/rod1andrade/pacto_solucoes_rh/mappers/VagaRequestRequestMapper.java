package io.github.rod1andrade.pacto_solucoes_rh.mappers;

import io.github.rod1andrade.pacto_solucoes_rh.entities.Vaga;
import io.github.rod1andrade.pacto_solucoes_rh.requests.VagaRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VagaRequestRequestMapper extends BaseRequestMapper<Vaga, VagaRequest> {
}
