package ru.cs.vsu.ast2_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cs.vsu.ast2_backend.exception.NotFoundException;
import ru.cs.vsu.ast2_backend.mapper.CarModelMapper;
import ru.cs.vsu.ast2_backend.mapper.PartMapper;
import ru.cs.vsu.ast2_backend.model.dto.CarModelDto;
import ru.cs.vsu.ast2_backend.model.dto.PartDto;
import ru.cs.vsu.ast2_backend.repository.CarModelRepository;
import ru.cs.vsu.ast2_backend.repository.PartRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartService {

    private final PartMapper partMapper;
    private final PartRepository partRepository;
    private final PartCategoryService partCategoryService;

    public List<PartDto> getAllParts() {
        return partRepository.findAll()
                .stream()
                .map(partMapper::toDto)
                .collect(Collectors.toList());
    }

}
