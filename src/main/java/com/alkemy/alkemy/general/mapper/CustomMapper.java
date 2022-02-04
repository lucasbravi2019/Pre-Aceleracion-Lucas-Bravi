package com.alkemy.alkemy.general.mapper;

import java.util.List;

public interface CustomMapper<Entity, DTO, BasicDTO, DetailedDTO> {

    Entity toEntity(DTO dto);
    Entity toEntity(DTO dto, Long id);

    BasicDTO toBasicDTO(Entity entity);
    DetailedDTO toDetailedDTO(Entity entity, boolean loadRelationship);
    List<BasicDTO> toBasicDTOList(List<Entity> entity);
    List<DetailedDTO> toDetailedDTOList(List<Entity> entity, boolean loadRelationship);


}
