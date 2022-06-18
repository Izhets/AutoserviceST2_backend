package ru.cs.vsu.ast2_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cs.vsu.ast2_backend.model.dto.PartCategoryDto;
import ru.cs.vsu.ast2_backend.model.dto.PartDto;
import ru.cs.vsu.ast2_backend.model.entity.part.PartEntity;
import ru.cs.vsu.ast2_backend.repository.PartRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartService {

    private final PartRepository partRepository;

    public List<PartDto> getAllParts() {
        List<PartEntity> partEntities = partRepository.findAll();
        return partEntities.stream().
                map(x -> PartDto.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .partCategory(
                                PartCategoryDto.builder()
                                        .id(x.getCategory()
                                                .getId())
                                        .name(x.getCategory().
                                                getName())
                                        .build())
                        .build()).collect(Collectors.toList());
    }


}
