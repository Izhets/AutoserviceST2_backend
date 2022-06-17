package ru.cs.vsu.ast2_backend.mapper;

import org.mapstruct.Mapper;
import ru.cs.vsu.ast2_backend.model.dto.CarTypeDto;
import ru.cs.vsu.ast2_backend.model.entity.car.CarTypeEntity;

@Mapper
public interface CarTypeMapper {

    CarTypeDto toDto(CarTypeEntity entity);

    CarTypeEntity toEntity(CarTypeDto dto);

}
