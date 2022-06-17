package ru.cs.vsu.ast2_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cs.vsu.ast2_backend.model.dto.CarModelDto;
import ru.cs.vsu.ast2_backend.service.CarModelService;
import ru.cs.vsu.ast2_backend.service.security.Roles;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car-models")
@Tag(name = "Модели авто", description = "API для работы с моделями автомобилей")
public class CarModelController {

    private final CarModelService carModelService;

    @GetMapping
    @RolesAllowed({Roles.USER, Roles.ADMIN})
    @Operation(summary = "Получить все модели", description = "Доступ: pk-user, pk-admin")
    public ResponseEntity<List<CarModelDto>> getAllModels() {
        return ResponseEntity.ok(carModelService.getAllCarModels());
    }
}
