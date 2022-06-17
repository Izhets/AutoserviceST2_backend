package ru.cs.vsu.ast2_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cs.vsu.ast2_backend.exception.BadRequestException;
import ru.cs.vsu.ast2_backend.exception.NotFoundException;
import ru.cs.vsu.ast2_backend.mapper.CarBrandMapper;
import ru.cs.vsu.ast2_backend.model.dto.CarBrandDto;
import ru.cs.vsu.ast2_backend.model.entity.car.CarBrandEntity;
import ru.cs.vsu.ast2_backend.repository.CarBrandRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarBrandService {

    private final CarBrandRepository carBrandRepository;
    private final CarBrandMapper carBrandMapper;

    public List<CarBrandDto> getAllBrands() {
        return carBrandRepository.findAll()
                .stream()
                .map(carBrandMapper::toDto)
                .collect(Collectors.toList());
    }

    public CarBrandEntity getBrandEntityById(UUID id) {
        return carBrandRepository.findById(id).orElseThrow(() -> {
            log.error("Car brand not found by id {}", id);
            throw new NotFoundException("Car brand not found by id %s", id);
        });
    }

    public CarBrandDto getBrandById(UUID id) {
        return carBrandMapper.toDto(getBrandEntityById(id));
    }

    public CarBrandDto addBrand(CarBrandDto carBrand) {
        if (carBrandRepository.existsByName(carBrand.getName())) {
            log.error("Car brand with name {} already exists", carBrand.getName());
            throw new BadRequestException("Car brand with name %s already exists", carBrand.getName());
        }

        var result = carBrandRepository.save(carBrandMapper.toEntity(carBrand));

        return carBrandMapper.toDto(result);

    }

    public void deleteCarBrand(UUID id) {
        var carBrandDto = getBrandById(id);

        carBrandRepository.deleteById(carBrandDto.getId());
    }
}
