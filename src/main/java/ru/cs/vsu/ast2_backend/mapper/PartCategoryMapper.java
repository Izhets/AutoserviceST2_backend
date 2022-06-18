package ru.cs.vsu.ast2_backend.mapper;

import org.mapstruct.Mapper;
import ru.cs.vsu.ast2_backend.model.dto.CarBrandDto;
import ru.cs.vsu.ast2_backend.model.dto.PartCategoryDto;
import ru.cs.vsu.ast2_backend.model.entity.car.CarBrandEntity;
import ru.cs.vsu.ast2_backend.model.entity.part.PartCategoryEntity;

import java.util.List;

@Mapper
public interface PartCategoryMapper {

    PartCategoryDto toDto(PartCategoryEntity entity);

    PartCategoryEntity toEntity(PartCategoryDto dto);

    List<PartCategoryDto> toDto(List<PartCategoryEntity> entities);

}
