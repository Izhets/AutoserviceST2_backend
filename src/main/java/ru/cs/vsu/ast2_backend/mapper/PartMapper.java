package ru.cs.vsu.ast2_backend.mapper;

import org.mapstruct.Mapper;
import ru.cs.vsu.ast2_backend.model.dto.PartDto;
import ru.cs.vsu.ast2_backend.model.entity.part.PartEntity;

import java.util.List;

@Mapper(uses = PartCategoryMapper.class)
public interface PartMapper {

    PartDto toDto(PartEntity entity);

    List<PartDto> toDto(List<PartEntity> entities);

    PartEntity toEntity(PartDto dto);
}
