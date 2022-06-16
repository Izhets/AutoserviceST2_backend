package ru.cs.vsu.ast2_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cs.vsu.ast2_backend.model.dto.TokenRequestDto;
import ru.cs.vsu.ast2_backend.model.dto.TokenResponseDto;
import ru.cs.vsu.ast2_backend.model.dto.UserCreateOrUpdateRequestDto;
import ru.cs.vsu.ast2_backend.model.dto.UserDto;
import ru.cs.vsu.ast2_backend.service.SecurityService;
import ru.cs.vsu.ast2_backend.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Авторизация")
public class AuthController {

    private final SecurityService securityService;
    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "Авторизация и получение токена по email/номеру телефона и паролю пользователя")
    public ResponseEntity<TokenResponseDto> getToken(@RequestBody TokenRequestDto requestDto) {
        return ResponseEntity.ok(securityService.getToken(requestDto));
    }

    @PostMapping("/register")
    @Operation(summary = "Регистрация пользователя")
    public ResponseEntity<UserDto> registration(@Valid @RequestBody UserCreateOrUpdateRequestDto registerRequestDto) {
        return new ResponseEntity<>(userService.registerUser(registerRequestDto), HttpStatus.CREATED);
    }


}
