package ru.cs.vsu.ast2_backend.mapper;

import org.mapstruct.Mapper;
import ru.cs.vsu.ast2_backend.model.dto.NewsDto;
import ru.cs.vsu.ast2_backend.model.entity.NewsEntity;

import java.util.List;

@Mapper
public interface NewsMapper {

    NewsEntity toEntity(NewsDto dto);

    List<NewsDto> toDto(List<NewsEntity> entity);

}
