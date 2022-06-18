package ru.cs.vsu.ast2_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cs.vsu.ast2_backend.model.dto.ReplenishBalanceRequestDto;
import ru.cs.vsu.ast2_backend.model.dto.UserCreateOrUpdateRequestDto;
import ru.cs.vsu.ast2_backend.model.dto.UserDto;
import ru.cs.vsu.ast2_backend.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@Tag(name = "Личный кабинет", description = "API для работы с личным кабинетом пользователя")
public class AccountController {

    private final UserService userService;

    @GetMapping
    @RolesAllowed("pk-user")
    @Operation(summary = "Получить информацию о своем аккаунте", description = "Доступ: pk-user")
    public ResponseEntity<UserDto> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }

    @PutMapping
    @RolesAllowed("pk-user")
    @Operation(summary = "Обновить информацию о своем аккаунте", description = "Доступ: pk-user")
    public ResponseEntity<UserDto> updateProfile(@Valid @RequestBody UserCreateOrUpdateRequestDto requestDto) {
        return ResponseEntity.ok(userService.updateProfile(requestDto));
    }

    @PostMapping("/replenish-balance")
    @RolesAllowed("pk-user")
    @Operation(summary = "Пополнить баланс", description = "Доступ: pk-user")
    public ResponseEntity<String> replenishBalance(@Valid @RequestBody ReplenishBalanceRequestDto requestDto) {
        return ResponseEntity.ok(userService.replenishBalance(requestDto));
    }

    @PostMapping("/withdrawMoney/{price}")
    @RolesAllowed("pk-user")
    @Operation(summary = "Оплатить баланс", description = "Доступ: pk-user")
    public ResponseEntity<UserDto> withdrawMoney(@PathVariable Integer price) {
        return ResponseEntity.ok(userService.withdrawMoney(price));
    }

}
