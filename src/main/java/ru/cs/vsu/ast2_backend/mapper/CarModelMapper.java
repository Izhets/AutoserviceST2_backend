package ru.cs.vsu.ast2_backend.mapper;

import org.mapstruct.Mapper;
import ru.cs.vsu.ast2_backend.model.dto.CarModelDto;
import ru.cs.vsu.ast2_backend.model.entity.car.CarModelEntity;

import java.util.List;

@Mapper(uses = CarBrandMapper.class)
public interface CarModelMapper {

    CarModelDto toDto(CarModelEntity entity);

    List<CarModelDto> toDto(List<CarModelEntity> entities);

    CarModelEntity toEntity(CarModelDto dto);
}
