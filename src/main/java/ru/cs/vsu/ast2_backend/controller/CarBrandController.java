package ru.cs.vsu.ast2_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cs.vsu.ast2_backend.model.dto.CarBrandDto;
import ru.cs.vsu.ast2_backend.model.dto.CarModelDto;
import ru.cs.vsu.ast2_backend.service.CarBrandService;
import ru.cs.vsu.ast2_backend.service.CarModelService;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car-brands")
@Tag(name = "Бренды", description = "API для работы с брендами автомобилей")
public class CarBrandController {

    private final CarBrandService carBrandService;
    private final CarModelService carModelService;

    @GetMapping
    @RolesAllowed({"pk-user", "pk-admin"})
    @Operation(summary = "Получить все бренды", description = "Доступ: pk-user, pk-admin")
    public ResponseEntity<List<CarBrandDto>> getAllBrands() {
        return ResponseEntity.ok(carBrandService.getAllBrands());
    }

    @GetMapping("/{id}")
    @RolesAllowed({"pk-user", "pk-admin"})
    @Operation(summary = "Получить бренд по id", description = "Доступ: pk-user, pk-admin")
    public ResponseEntity<CarBrandDto> getBrandById(@PathVariable UUID id) {
        return ResponseEntity.ok(carBrandService.getBrandById(id));
    }

    @PostMapping
    @RolesAllowed("pk-admin")
    @Operation(summary = "Добавить новый бренд", description = "Доступ: pk-admin")
    public ResponseEntity<CarBrandDto> addCarBrand(@RequestBody CarBrandDto carBrand) {
        return new ResponseEntity<>(carBrandService.addBrand(carBrand), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("pk-admin")
    @Operation(summary = "Удалить бренд", description = "Доступ: pk-admin")
    public ResponseEntity<Void> deleteCarBrand(@PathVariable UUID id) {
        carBrandService.deleteCarBrand(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/models")
    @RolesAllowed({"pk-user", "pk-admin"})
    @Operation(summary = "Получить модели автомобилей по бренду", description = "Доступ: pk-user, pk-admin")
    public ResponseEntity<List<CarModelDto>> getModelsById(@PathVariable UUID id) {
        return ResponseEntity.ok(carModelService.getModelsByBrandId(id));
    }


    @PostMapping("/{id}/models")
    @RolesAllowed("pk-admin")
    @Operation(summary = "Добавить модель автомобиля", description = "Доступ: pk-admin")
    public ResponseEntity<CarModelDto> addModel(@PathVariable UUID id,
                                                @RequestBody CarModelDto carModelDto) {
        return ResponseEntity.ok(carModelService.addModel(id, carModelDto));
    }

    @DeleteMapping("/{id}/models/{modelId}")
    @RolesAllowed("pk-admin")
    @Operation(summary = "Удалить модель автомобиля", description = "Доступ: pk-admin")
    public ResponseEntity<CarModelDto> deleteModel(@PathVariable UUID id,
                                                   @PathVariable UUID modelId) {
        carModelService.deleteModel(id, modelId);
        return ResponseEntity.ok().build();
    }


}
