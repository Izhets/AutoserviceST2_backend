package ru.cs.vsu.ast2_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cs.vsu.ast2_backend.exception.NotFoundException;
import ru.cs.vsu.ast2_backend.mapper.CarMapper;
import ru.cs.vsu.ast2_backend.model.dto.CarDto;
import ru.cs.vsu.ast2_backend.repository.CarRepository;
import ru.cs.vsu.ast2_backend.util.SecurityUtil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static ru.cs.vsu.ast2_backend.util.SecurityUtil.getUserId;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarTypeService carTypeService;
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public List<CarDto> getCarsByUser() {
        return carRepository.findAllByOwnerId(getUserId())
                .stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    public CarDto getUserCarById(UUID id) {
        var carEntity = carRepository.findByIdAndOwnerId(id, getUserId()).orElseThrow(() -> {
            log.error("Car not found by id {} and userId {}", id, getUserId());
            throw new NotFoundException("Car not found by id %s and userId %s", id, SecurityUtil.getUserId());
        });

        return carMapper.toDto(carEntity);
    }

    public CarDto getCarById(UUID id) {
        var carEntity = carRepository.findById(id).orElseThrow(() -> {
            log.error("Car not found by id={} ", id);
            throw new NotFoundException("Car not found by id=%s ", id);
        });

        return carMapper.toDto(carEntity);
    }

    public CarDto addCar(CarDto carDto) {
        carDto.setOwnerId(getUserId());

        var carEntity = carRepository.save(carMapper.toEntity(carDto));

        return carMapper.toDto(carEntity);
    }

    public CarDto updateCar(UUID id, CarDto carDto) {
        var updatedDto = carMapper.updateDto(getUserCarById(id), carDto);

        var result = carRepository.save(carMapper.toEntity(updatedDto));

        return carMapper.toDto(result);
    }

    @Transactional
    public void deleteCar(UUID id) {
        var userCar = getUserCarById(id);
        carRepository.deleteById(userCar.getId());
    }

    public List<UUID> getUserCarIds() {
        return carRepository.findCarIdsByUserId(getUserId());
    }

    public boolean isUserCar(UUID carId, UUID userId) {
        return carRepository.existsByIdAndOwnerId(carId, userId);
    }

}
