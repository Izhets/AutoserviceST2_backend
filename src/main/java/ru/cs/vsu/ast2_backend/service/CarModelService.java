package ru.cs.vsu.ast2_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cs.vsu.ast2_backend.exception.NotFoundException;
import ru.cs.vsu.ast2_backend.mapper.CarModelMapper;
import ru.cs.vsu.ast2_backend.model.dto.CarModelDto;
import ru.cs.vsu.ast2_backend.repository.CarModelRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarModelService {

    private final CarModelMapper carModelMapper;
    private final CarModelRepository carModelRepository;
    private final CarBrandService carBrandService;

    public List<CarModelDto> getAllCarModels() {
        return carModelRepository.findAll()
                .stream()
                .map(carModelMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<CarModelDto> getModelsByBrandId(UUID brandId) {
        var carBrandDto = carBrandService.getBrandById(brandId);

        return carModelRepository.findAllByBrandId(carBrandDto.getId())
                .stream()
                .map(carModelMapper::toDto)
                .collect(Collectors.toList());

    }

    public CarModelDto getModelByBrandIdAndId(UUID brandId, UUID modelId) {
        carBrandService.getBrandEntityById(brandId);

        var carModelEntity = carModelRepository.findById(modelId).orElseThrow(() -> {
            log.error("Car model not found by id {}", modelId);
            throw new NotFoundException("Car model not found by id %s", modelId);
        });

        return carModelMapper.toDto(carModelEntity);

    }

    public CarModelDto addModel(UUID brandId, CarModelDto carModelDto) {
        var carBrandEntity = carBrandService.getBrandEntityById(brandId);

        var carModelEntity = carModelMapper.toEntity(carModelDto);
        carModelEntity.setBrand(carBrandEntity);

        return carModelMapper.toDto(carModelRepository.save(carModelEntity));

    }

    public void deleteModel(UUID brandId, UUID modelId) {
        var carModelDto = getModelByBrandIdAndId(brandId, modelId);

        carModelRepository.deleteById(carModelDto.getId());
    }

}
