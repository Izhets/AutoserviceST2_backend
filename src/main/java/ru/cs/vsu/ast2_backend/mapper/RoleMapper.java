package ru.cs.vsu.ast2_backend.mapper;

import org.mapstruct.Mapper;
import ru.cs.vsu.ast2_backend.model.dto.RoleDto;
import ru.cs.vsu.ast2_backend.model.entity.RoleEntity;

@Mapper
public interface RoleMapper {

    RoleDto toDto(RoleEntity entity);

    RoleEntity toEntity(RoleDto dto);

}
