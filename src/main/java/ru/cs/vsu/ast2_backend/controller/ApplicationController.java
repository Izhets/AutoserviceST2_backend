package ru.cs.vsu.ast2_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cs.vsu.ast2_backend.model.dto.ApplicationDto;
import ru.cs.vsu.ast2_backend.model.dto.CarDto;
import ru.cs.vsu.ast2_backend.service.ApplicationService;
import ru.cs.vsu.ast2_backend.service.CarService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applications")
@Tag(name = "Заявки", description = "API для работы с заявками")
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping
    @RolesAllowed("pk-user")
    @Operation(summary = "Получить заявки авторизованного пользователя", description = "Доступ: pk-user")
    public ResponseEntity<List<ApplicationDto>> getAllApplicationsByUser() {
        return ResponseEntity.ok(applicationService.getApplicationsByUser());
    }

    @GetMapping("/{id}")
    @RolesAllowed("pk-user")
    @Operation(summary = "Получить заявку авторизованного пользователя по id", description = "Доступ: pk-user")
    public ResponseEntity<ApplicationDto> getUserApplicationById(@PathVariable UUID id) {
        return ResponseEntity.ok(applicationService.getUserApplicationById(id));
    }

    @PostMapping
    @RolesAllowed("pk-user")
    @Operation(summary = "Добавление заявки авторизованным пользователем", description = "Доступ: pk-user")
    public ResponseEntity<ApplicationDto> addApplication(@RequestBody @Valid ApplicationDto applicationDto) {
        return new ResponseEntity<>(applicationService.addApplication(applicationDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @RolesAllowed("pk-user")
    @Operation(summary = "Обновление заявки", description = "Доступ: pk-admin")
    public ResponseEntity<ApplicationDto> updateApplication(@PathVariable UUID id,
                                            @RequestBody @Valid ApplicationDto applicationDto) {
        return new ResponseEntity<>(applicationService.updateApplication(id, applicationDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("pk-user")
    @Operation(summary = "Удаление своей заявки пользователем", description = "Доступ: pk-user")
    public ResponseEntity<Void> deleteApplication(@PathVariable UUID id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.ok().build();
    }


}
