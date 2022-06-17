package ru.cs.vsu.ast2_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cs.vsu.ast2_backend.exception.BadRequestException;
import ru.cs.vsu.ast2_backend.exception.NotFoundException;
import ru.cs.vsu.ast2_backend.mapper.CarTypeMapper;
import ru.cs.vsu.ast2_backend.model.dto.CarTypeDto;
import ru.cs.vsu.ast2_backend.model.entity.car.CarTypeEntity;
import ru.cs.vsu.ast2_backend.repository.CarTypeRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarTypeService {

    private final CarTypeRepository carTypeRepository;
    private final CarTypeMapper carTypeMapper;

    public List<CarTypeDto> getAllTypes() {
        return carTypeRepository.findAll()
                .stream()
                .map(carTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    public CarTypeDto getCarTypeById(UUID id) {
        var carTypeEntity = carTypeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Car type not found by id {}", id);
                    throw new NotFoundException("Car type not found by id " + id);
                });

        return carTypeMapper.toDto(carTypeEntity);
    }

    public CarTypeEntity getCarType(String carType) {
        return carTypeRepository.findByType(carType)
                .orElseThrow(() -> {
                    log.error("Car type not found by type {}", carType);
                    throw new NotFoundException("Car type not found by type " + carType);
                });
    }

    public CarTypeDto addCarType(CarTypeDto carTypeDto) {
        if (carTypeRepository.existsByType(carTypeDto.getType())) {
            log.error("Car type {} already exists", carTypeDto.getType());
            throw new BadRequestException("Car type %s already exists", carTypeDto.getType());
        }
        var saveResult = carTypeRepository.save(carTypeMapper.toEntity(carTypeDto));

        return carTypeMapper.toDto(saveResult);
    }

    public CarTypeDto updateCarType(UUID id, CarTypeDto carTypeDto) {
        var currentCarType = getCarTypeById(id);
        currentCarType.setType(carTypeDto.getType());

        var result = carTypeRepository.save(carTypeMapper.toEntity(currentCarType));

        return carTypeMapper.toDto(result);
    }

    public void deleteCarType(UUID id) {
        if (!carTypeRepository.existsById(id)) {
            log.error("Car type not found by id {}", id);
            throw new NotFoundException("Car type not found by id " + id);
        }
        carTypeRepository.deleteById(id);
    }
}
