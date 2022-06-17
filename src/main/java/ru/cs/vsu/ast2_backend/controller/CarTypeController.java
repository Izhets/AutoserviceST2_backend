package ru.cs.vsu.ast2_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cs.vsu.ast2_backend.model.dto.CarTypeDto;
import ru.cs.vsu.ast2_backend.service.CarTypeService;
import ru.cs.vsu.ast2_backend.service.security.Roles;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car-types")
@Tag(name = "Типы автомобилей", description = "API для работы с типами автомобилей")
public class CarTypeController {

    private final CarTypeService carTypeService;

    @GetMapping
    @RolesAllowed({Roles.ADMIN, Roles.USER, Roles.ANONYMOUS})
    @Operation(summary = "Получить все типы автомобилей", description = "Доступ: pk-user, pk-admin, anonymous")
    public ResponseEntity<List<CarTypeDto>> getAllTypes() {
        return ResponseEntity.ok(carTypeService.getAllTypes());
    }

    @GetMapping("/{id}")
    @RolesAllowed({Roles.ADMIN, Roles.USER, Roles.ANONYMOUS})
    @Operation(summary = "Получить тип авто по id", description = "Доступ: pk-user, pk-admin, , anonymous")
    public ResponseEntity<CarTypeDto> getTypeById(@PathVariable UUID id) {
        return ResponseEntity.ok(carTypeService.getCarTypeById(id));
    }

    @PostMapping
    @RolesAllowed(Roles.ADMIN)
    @Operation(summary = "Добавить новый тип авто", description = "Доступ: pk-admin")
    public ResponseEntity<CarTypeDto> addCarType(@RequestBody CarTypeDto carTypeDto) {
        return new ResponseEntity<>(carTypeService.addCarType(carTypeDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @RolesAllowed(Roles.ADMIN)
    @Operation(summary = "Добавить новый тип авто", description = "Доступ: pk-admin")
    public ResponseEntity<CarTypeDto> updateCarType(@PathVariable UUID id,
                                                    @RequestBody CarTypeDto carTypeDto) {
        return ResponseEntity.ok(carTypeService.updateCarType(id, carTypeDto));
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("pk-admin")
    @Operation(summary = "Удалить тип", description = "Доступ: pk-admin")
    public ResponseEntity<Void> deleteCarType(@PathVariable UUID id) {
        carTypeService.deleteCarType(id);
        return ResponseEntity.ok().build();
    }

}
