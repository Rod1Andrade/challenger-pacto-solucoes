package io.github.rod1andrade.pacto_solucoes_rh.mappers;

import java.util.List;

public interface BaseRequestMapper<Entity, Request> {
    Entity toEntity(Request dto);
    List<Entity> toEntity(List<Request> dtos);
}
