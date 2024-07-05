package io.github.rod1andrade.pacto_solucoes_rh.mappers;

import java.util.List;

public interface BaseResponseMapper<Entity, Response> {
    Response toDto(Entity entity);
    List<Response> toDto(List<Entity> entity);
}
