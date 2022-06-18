package ru.cs.vsu.ast2_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cs.vsu.ast2_backend.model.dto.PartCategoryDto;
import ru.cs.vsu.ast2_backend.service.PartCategoryService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/part-category")
@Tag(name = "Категории запчастей", description = "API для работы с категориями запчастей")
public class PartCategoryController {

    private final PartCategoryService partCategoryService;

    @GetMapping
    @RolesAllowed({"pk-user", "pk-admin"})
    @Operation(summary = "Получить все категории запчастей", description = "Доступ: pk-user, pk-admin")
    public ResponseEntity<List<PartCategoryDto>> getAllPartCategories() {
        return ResponseEntity.ok(partCategoryService.getAllPartCategories());
    }

}
