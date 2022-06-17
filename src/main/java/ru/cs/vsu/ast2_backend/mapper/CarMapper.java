package ru.cs.vsu.ast2_backend.mapper;

import org.mapstruct.*;
import ru.cs.vsu.ast2_backend.model.dto.CarDto;
import ru.cs.vsu.ast2_backend.model.entity.car.CarEntity;

@Mapper
public interface CarMapper {

    CarDto toDto(CarEntity entity);

    @Mapping(source = "ownerId", target = "owner.id")
    CarEntity toEntity(CarDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CarDto updateDto(@MappingTarget CarDto oldDto, CarDto dto);

}
