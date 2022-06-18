package ru.cs.vsu.ast2_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cs.vsu.ast2_backend.model.dto.CarModelDto;
import ru.cs.vsu.ast2_backend.model.dto.PartDto;
import ru.cs.vsu.ast2_backend.service.CarModelService;
import ru.cs.vsu.ast2_backend.service.PartService;
import ru.cs.vsu.ast2_backend.service.security.Roles;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/part")
@Tag(name = "Запчасти", description = "API для работы с моделями автомобилей")
public class PartController {

    private final PartService partService;

    @GetMapping
    @RolesAllowed({Roles.USER, Roles.ADMIN})
    @Operation(summary = "Получить все запчасти", description = "Доступ: pk-user, pk-admin")
    public ResponseEntity<List<PartDto>> getAllParts() {
        return ResponseEntity.ok(partService.getAllParts());
    }

}
