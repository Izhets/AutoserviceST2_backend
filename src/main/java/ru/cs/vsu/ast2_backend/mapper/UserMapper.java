package ru.cs.vsu.ast2_backend.mapper;

import org.mapstruct.*;
import ru.cs.vsu.ast2_backend.model.dto.UserCreateOrUpdateRequestDto;
import ru.cs.vsu.ast2_backend.model.dto.UserDto;
import ru.cs.vsu.ast2_backend.model.entity.UserEntity;

@Mapper
public interface UserMapper {

    UserEntity toEntity(UserCreateOrUpdateRequestDto registerRequestDto);

    UserEntity toEntity(UserDto dto);

    UserDto toDto(UserEntity entity);

    @Mapping(target = "email", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity updateDto(@MappingTarget UserEntity target, UserCreateOrUpdateRequestDto dto);

}
