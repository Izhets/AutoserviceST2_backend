package ru.cs.vsu.ast2_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cs.vsu.ast2_backend.mapper.PartCategoryMapper;
import ru.cs.vsu.ast2_backend.model.dto.PartCategoryDto;
import ru.cs.vsu.ast2_backend.repository.PartCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartCategoryService {

    private final PartCategoryRepository partCategoryRepository;
    private final PartCategoryMapper partCategoryMapper;

    public List<PartCategoryDto> getAllPartCategories() {
        return partCategoryRepository.findAll()
                .stream()
                .map(partCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

}
