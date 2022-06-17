package ru.cs.vsu.ast2_backend.mapper;

import org.mapstruct.Mapper;
import ru.cs.vsu.ast2_backend.model.dto.CarBrandDto;
import ru.cs.vsu.ast2_backend.model.entity.car.CarBrandEntity;

import java.util.List;

@Mapper
public interface CarBrandMapper {

    CarBrandDto toDto(CarBrandEntity entity);

    CarBrandEntity toEntity(CarBrandDto dto);

    List<CarBrandDto> toDto(List<CarBrandEntity> entities);

}
