package ru.cs.vsu.ast2_backend.mapper;

import org.mapstruct.*;
import ru.cs.vsu.ast2_backend.model.dto.ApplicationDto;
import ru.cs.vsu.ast2_backend.model.dto.CarDto;
import ru.cs.vsu.ast2_backend.model.entity.ApplicationEntity;

import java.util.List;

@Mapper
public interface ApplicationMapper {

    ApplicationDto toDto(ApplicationEntity entity);

    List<ApplicationDto> toDto(List<ApplicationEntity> entities);

    ApplicationEntity toEntity(ApplicationDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ApplicationDto updateDto(@MappingTarget ApplicationDto oldDto, ApplicationDto dto);

}
