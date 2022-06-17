package ru.cs.vsu.ast2_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cs.vsu.ast2_backend.model.dto.CarDto;
import ru.cs.vsu.ast2_backend.service.CarService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
@Tag(name = "Автомобили", description = "API для работы с автомобилями")
public class CarController {

    private final CarService carService;

    @GetMapping
    @RolesAllowed("pk-user")
    @Operation(summary = "Получить автомобили авторизованного пользователя", description = "Доступ: pk-user")
    public ResponseEntity<List<CarDto>> getAllCarsByUser() {
        return ResponseEntity.ok(carService.getCarsByUser());
    }

    @GetMapping("/{id}")
    @RolesAllowed("pk-user")
    @Operation(summary = "Получить автомобиль авторизованного пользователя по id", description = "Доступ: pk-user")
    public ResponseEntity<CarDto> getUserCarById(@PathVariable UUID id) {
        return ResponseEntity.ok(carService.getUserCarById(id));
    }

    @PostMapping
    @RolesAllowed("pk-user")
    @Operation(summary = "Добавление автомобиля авторизованным пользователем", description = "Доступ: pk-user")
    public ResponseEntity<CarDto> addCar(@RequestBody @Valid CarDto carDto) {
        return new ResponseEntity<>(carService.addCar(carDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @RolesAllowed("pk-user")
    @Operation(summary = "Обновление о своем авто авторизованным пользователем", description = "Доступ: pk-user")
    public ResponseEntity<CarDto> updateCar(@PathVariable UUID id,
                                            @RequestBody @Valid CarDto carDto) {
        return new ResponseEntity<>(carService.updateCar(id, carDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("pk-user")
    @Operation(summary = "Удаление своего авто пользователем", description = "Доступ: pk-user")
    public ResponseEntity<Void> deleteCar(@PathVariable UUID id) {
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }


}
